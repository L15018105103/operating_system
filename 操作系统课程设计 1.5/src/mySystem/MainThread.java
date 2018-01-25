package mySystem;

import main.Main;
import mySystem.cpu.command.Order;
import mySystem.memory.Block;

public class MainThread extends Thread {
	int redColorBlock = -1;
	int timeSlice = 1;
	String runningIndex = "-1";
	String deadJobId = ""; // 记录刚刚已完成的作业id，用来通知前端把该作业的界面删除
	boolean blockFinishFlag = false;

	// 时间片轮转执行PCB列表中的各个作业,每次只执行一个作业的一条指令
	public String excute() { // 执行指令
		System.out.println("正在执行");
		// 1. 对上一次的作业情况进行后续处理
		if (Main.mySystem.runIndex != -1) {
			// 先把上一次作业的运行态改为就绪态
			Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).state = 1;
			if (blockFinishFlag == true) {
				System.out.println("重置该块，重置了jobBlockList");
				// 重置该块
				Main.mySystem.memory.blockList.set(
						Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobBlockList.get(0),
						new Block(Main.mySystem.memory.blockNum, Main.mySystem.memory.blockSize));
				// 执行完后，把PCBList的jobBlockList的首元素删掉
				Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobBlockList.remove(0);
				if (Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobBlockList.size() == 0) {
					System.out.println("已完成:" + Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobId);
					// 更新标记，通知前端的界面要删除该作业
					deadJobId = String.valueOf(Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobId);
					Main.mySystem.memory.PCBList.remove(Main.mySystem.runIndex); // 清除PCB的最近已完成作业
					Main.mySystem.runIndex--;
					blockFinishFlag = false;
					return "-1";
				}
				blockFinishFlag = false;
			}
		}
		if (timeSlice == Main.mySystem.clock.timeSlice) {
			Main.mySystem.runIndex++;
			Main.mySystem.runIndex %= Main.mySystem.memory.PCBList.size(); // 更新即将要执行的作业的PCB队列下标
			timeSlice = 1; // 时间片记录归一
		} else {
			if (Main.mySystem.runIndex == -1) {
				Main.mySystem.runIndex++;
				Main.mySystem.runIndex %= Main.mySystem.memory.PCBList.size(); // 更新即将要执行的作业的PCB队列下标
			}
			timeSlice++;
		}
		// 2. 执行下一条指令前，先处理设备
		for(int i=0;i<Main.mySystem.devices.size();i++){
			if(Main.mySystem.devices.get(i).state==1){
				if(Main.mySystem.devices.get(i).usedTime<Main.mySystem.devices.get(i).useTimeSlice){
					Main.mySystem.devices.get(i).usedTime++; // 设备占用时间加1
				}
				else{
					Main.mySystem.devices.get(i).state=0; // 若执行完，则标记设备为空闲
					Main.mySystem.devices.get(i).usedTime=0;
					Main.mySystem.devices.get(i).usedJobId="";
				}
			}
		}
		// 发送设备状态信息给前端
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		for(int i=0;i<Main.mySystem.devices.size();i++){
			str1=str1+Main.mySystem.devices.get(i).deviceName+"\n";
			str2=str2+Main.mySystem.devices.get(i).state+"\n";
			str3=str3+Main.mySystem.devices.get(i).usedJobId+"\n";
			str4=str4+Main.mySystem.devices.get(i).waitingJobId+"\n";
			str5=str5+Main.mySystem.devices.get(i).usedTime+"\n";
		}
		if(Main.mySystem.incoming[1]!=null){
			Order.send(Main.mySystem.incoming[1], 6, 4, str1, str2, str3, str4,str5); // 向前端发送设备信息，注意要用另一个连接
		}
		// 3. 执行下一条指令：
		// 执行的准备阶段：先去寻找要执行的块号
		int index = -1;
		if (Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobBlockList.size() != 0) {
			index = Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobBlockList.get(0);
		}
		System.out.println("作业所在块号为：" + index);
		if (index == -1) {
			return "-1";
		}
		// index即为正在执行的块号
		int index1 = 0;
		// 开始执行指令：进入对应的内存块遍历内存单元执行指令
		for (int j = 0; j < Main.mySystem.memory.blockList.get(index).memoryList.size(); j++) {
			if (!Main.mySystem.memory.blockList.get(index).memoryList.get(j).hasExcuted) {
				index1 = j;
				System.out.println("执行了：" + Main.mySystem.memory.blockList.get(index).memoryList.get(j).value);
				//调用指令的方法
				executeOrder(Main.mySystem.memory.blockList.get(index).memoryList.get(j).value);
				Main.mySystem.memory.blockList.get(index).memoryList.get(j).hasExcuted = true;
				break;
			}
		}
		// index1即为正在执行的块内的内存单元编号
		// 把这次的作业的就绪态改为运行态
		Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).state = 2;
		// 更新所有作业的等待时间
		for (int i = 0; i < Main.mySystem.memory.PCBList.size(); i++) {
			if (i == Main.mySystem.runIndex) {
				Main.mySystem.memory.PCBList.get(i).waitTime = 0; // 设置正在执行的那个作业的waitTime为0
			} else {
				Main.mySystem.memory.PCBList.get(i).waitTime++; // 非正在执行的作业的waitTime加一
			}
		}
		// 更新块的canBeCoveredFlag
		for (int i = 0; i < Main.mySystem.memory.blockList.get(index).memoryList.size(); i++) {
			if (Main.mySystem.memory.blockList.get(index).memoryList.get(i).value != null) { // 若内存单元非空
				if (Main.mySystem.memory.blockList.get(index).memoryList.get(i).hasExcuted) { // 若非空的内存单元已被执行
					blockFinishFlag = true;
				} else {
					blockFinishFlag = false;
				}
			}
		}
		// System.out.println("本次执行了第：" + index + "号块第" + index1 + "个内存单元："+
		// Main.mySystem.memory.blockList.get(index).memoryList.get(index1).value);
		// 发送左下角的内容
		{
			if (index != -1 && index1 != -1) {
				System.out.println("发了5 1");
			}
			Order.send(Main.mySystem.incoming[0], 5, 1,
					Main.mySystem.memory.blockList.get(index).memoryList.get(index1).value);
		}
		// 更新PCB的percentage
		Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).percentage++;
		return String.valueOf(index) + String.valueOf(index1);
	}

	// 虚拟操作系统一直运作的线程
	public void run() {
		long oldTime = 0;
		while (Main.mySystem.incoming[0].isOpen()) {
			// 根据时钟执行，检查时钟是否调到下一拍，若不是，则跳过不执行
			if (oldTime == Main.mySystem.clock.getTime()) // 当两次时间不一样时才发送时间
			{
				continue; // 下面代码均为根据时钟周期进行执行，因此当时钟还没到下一拍时，不执行下面的代码
			}
			// 发送时钟
			{
				// System.out.println(Main.mySystem.clock.getTime());
				Order.send(Main.mySystem.incoming[0], 1, 2, String.valueOf(Main.mySystem.clock.getTime())); // 调用的是父类的方法
				oldTime = Main.mySystem.clock.getTime();
				// 发送相对时钟
				Order.send(Main.mySystem.incoming[0], 1, 1, String.valueOf(Main.mySystem.clock.timeSlice - timeSlice)); // 调用的是父类的方法
			}
			// 更新内存
			{
				// 时刻检查缓冲区，把缓冲区读入内存
				// 先把二级缓存的内容转移回一级缓存中
				while (Main.mySystem.memory.buffer2.size() != 0) {
					Main.mySystem.memory.buffer.add(0,
							Main.mySystem.memory.buffer2.get(Main.mySystem.memory.buffer2.size() - 1));
					Main.mySystem.memory.buffer2.remove(Main.mySystem.memory.buffer2.size() - 1);
				}
				if (Main.mySystem.memory.buffer.size() != 0) { // 若缓冲区不为空，则把缓冲区的内容写入内存
					redColorBlock = Main.mySystem.memory.addValue();
				}
			}
			// 发送PCB的内容
			{
				String str1 = "";
				String str2 = "";
				String str3 = "";
				String str4 = "";
				for (int i = 0; i < Main.mySystem.memory.PCBList.size(); i++) {
					str1 += Main.mySystem.memory.PCBList.get(i).jobId + "\n";
					if (Main.mySystem.memory.PCBList.get(i).state == 1)
						str2 += "就绪态\n";
					else if (Main.mySystem.memory.PCBList.get(i).state == 2)
						str2 += "运行态\n";
					str3 += Main.mySystem.memory.PCBList.get(i).waitTime + "\n";
					str4 += 100 * Main.mySystem.memory.PCBList.get(i).percentage
							/ Main.mySystem.memory.PCBList.get(i).jobLength + "\n";
					System.out.println("percentage=" + Main.mySystem.memory.PCBList.get(i).percentage);
					System.out.println("jobLength=" + Main.mySystem.memory.PCBList.get(i).jobLength);
					System.out.println(Main.mySystem.memory.PCBList.get(i).jobId + "的进度为："
							+ (Main.mySystem.memory.PCBList.get(i).percentage
									/ Main.mySystem.memory.PCBList.get(i).jobLength));
				}
				if (!deadJobId.equals("")) {
					str1 += deadJobId;
					str2 += "退出态";
					str3 += "0";
					str4 += "101";
				}
				Order.send(Main.mySystem.incoming[0], 2, 2, str1, str2, str3, str4);
			}
			// 发送内存的内容
			{
				// 执行作业
				if (Main.mySystem.memory.PCBList.size() != 0) { // 若PCBList不为空则执行作业
					runningIndex = excute(); // 根据PCB进行时间片轮转的调度方式执行作业
				}
				Order.send(Main.mySystem.incoming[0], 4, 4, runningIndex, "" + redColorBlock,
						Main.mySystem.memory.getValue()); // 向前端发送当前内存的内容，发送头为4
				// 发送百分比
				int count = 0;
				for (int i = 0; i < Main.mySystem.memory.blockList.size(); i++) {
					if (!Main.mySystem.memory.blockList.get(i).canBeCoveredFlag) {
						count++;
					}
				}
				Order.send(Main.mySystem.incoming[0], 5, 3, "" + count); // 发送内存百分比
			}
		}
		// 最后要关闭线程
		this.interrupt();
		Main.systemFlag = true;
		System.out.println("已关闭");
	}

	// 解析执行mov ax,0等指令
	void executeOrder(String order) {
		// mov add sub out cmp mul use
		// 1.先把所有的逗号变成空格
		if (order.contains(",")) {
			order = order.replace(',', ' ');
		}
		// 2.分割字符串
		String[] result = order.split(" "); // 利用空格分割字符串
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		// 3.执行与设备有关的指令 use A1 10
		if (result.length == 3 && result[0].matches("[Uu][Ss][Ee]")&&result[2].matches("[0-9]+")) {
			int index=-1;
			for(int i=0;i<Main.mySystem.devices.size();i++){ // 遍历设备，找到对应的设备
				if(Main.mySystem.devices.get(i).deviceName.equals(result[1])){
					index=i;
					break;
				}
			}
			if(index!=-1){
				use(index,Integer.valueOf(result[2])); //调用use指令的方法
			}
			else{
				System.out.println("非法指令！");
				//5 1通知前端，并中断当前指令
				Order.send(Main.mySystem.incoming[0], 5, 1, "非法指令！"); // 发送内存百分比
			}
		}
		// 4.不考虑use 设备的指令，只考虑与寄存器有关的指令：
		// 检查指令第一个操作数是否合法
		if (!result[1].matches("[abcdABCD][xX]")) {
			System.out.println("非法指令！");
		} else {
			// 4.执行与寄存器有关的指令
			if (result.length == 2) { // 单操作数指令
				if(result[0].equals("out")){
					Order.send(Main.mySystem.incoming[0], 5, 2, ""+Main.mySystem.register.ax);
				}
			} else if (result.length == 3) { // 双操作数指令

			} else {
				System.out.println("非法指令！");
			}
		}
	}

	public synchronized void use(int index,int time) { // 使用设备的方法（use指令）
		Main.mySystem.devices.get(index).state=1; // 设置设备状态
		Main.mySystem.devices.get(index).useTimeSlice=time; //设置最长占用时间
		Main.mySystem.devices.get(index).usedJobId=""+Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).jobId; // 设置占用该设备的作业id
		Main.mySystem.memory.PCBList.get(Main.mySystem.runIndex).state=3; // 设置作业为阻塞态
	}
}
