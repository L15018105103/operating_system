package mySystem.memory;

import java.util.ArrayList;

import main.Main;

/**
 * 内存模块
 * @author a7840 实例化时传入内存划分块的数量及块的大小
 * 
 */

public class Memory {
	public int blockNum; // 块数量
	public int blockSize; // 块大小
	public ArrayList<Block> blockList = new ArrayList<Block>(); // 维护一个内存块列表
	public ArrayList<PCBBit> PCBList = new ArrayList<PCBBit>(); // 维护一个PCB
	public ArrayList<BufferBit> buffer = new ArrayList<BufferBit>(); // 维护一个页表缓冲区
	public ArrayList<BufferBit> buffer2 = new ArrayList<BufferBit>(); // 二级缓存
	private String memoryValue; // 一个外部可以访问的内存的所有值

	// 初始化内存：创建内存单元->创建块
	public Memory(int blockNum, int blockSize) {
		// 初始化块数和块大小
		this.blockNum = blockNum;
		this.blockSize = blockSize;
		for (int i = 0; i < blockNum; i++) { // 初始化内存
			blockList.add(new Block(blockNum, blockSize)); // 添加块
		}
	}

	public String getValue() { // 获取内存内容
		this.memoryValue = "";
		for (int i = 0; i < this.blockList.size(); i++) {
			for (int j = 0; j < this.blockList.get(i).memoryList.size(); j++) {
				this.memoryValue = this.memoryValue + this.blockList.get(i).memoryList.get(j).value + "\n"; // 以"\n"分割
			}
		}
		return this.memoryValue;
	}

	// 页表缓冲区（没大小限制）,读文件时先写入缓冲区，由虚拟操作系统的主线程负责把缓冲区的内容读入内存
	public void addBuffer(int jobId, String value, double length) {
		buffer.add(new BufferBit(jobId, value,length)); // 把作业的代码加到缓冲区中
	}
	
	// 向内存添加值（运用首次适配方法和先进先出的页表替换算法）,只添加一个块,返回覆盖了哪个块
	public int addValue() {
		for (int i = 0; i < blockList.size(); i++) {
			// 直接找到首个能被覆盖的的块加进去
			if (blockList.get(i).canBeCoveredFlag) { // 如果“能被覆盖标记”为true，则进行覆盖
				int oldJobId = buffer.get(0).jobId; // 记录即将要写进内存的作业id，以确保一个块的内存单元只存储一个作业
				Main.mySystem.memory.addPCB(oldJobId,buffer.get(0).jobLength); //是否接受该作业的标记（若PCB中已存在该作业则为false）
				if(PCBList.get(PCBList.size()-1).jobBlockList.size()<Main.mySystem.allotNum){ //若分配数还没到达作业分配页表数的上限
					PCBList.get(PCBList.size()-1).jobBlockList.add(i); // 把作业写入的块号写进PCB的作业运行队列
				}
				else{ //若分配数已达到作业最大分配页表数，则把该作业放到二级缓存内
					buffer2.add(buffer.get(0));
					buffer.remove(0);
					return -1;
				}
				for (int j = 0; j < blockList.get(i).memoryList.size(); j++) {
					if (buffer.size() == 0) {
						break;
					}
					if (oldJobId == buffer.get(0).jobId) {
						blockList.get(i).memoryList.get(j).hasExcuted = false;
						blockList.get(i).memoryList.get(j).value = buffer.get(0).value;
						System.out.println("加进了第"+i+"个块第"+j+"个单元");
						buffer.remove(0); // 立即把缓冲区（缓冲对列）的首个元素删除
					} else {
						break; // 当即将加入内存单元的作业id跟块的作业id不一致时，不予以加入
					}
				}
				blockList.get(i).jobId=oldJobId; //写入jobId
				blockList.get(i).canBeCoveredFlag = false; // 设置标记为false，表明该块还没被执行完，不能被覆盖
				return i; // 加了一次之后应当退出,因为一个时钟周期内只能执行一次修改内存的操作
			}
		}
		return -1; // 若没覆盖，则返回-1
	}

	public boolean addPCB(int jobId,double jobLength) { // 把作业加进PCB
		for (int i = 0; i < PCBList.size(); i++) {
			if (PCBList.get(i).jobId == jobId) {
				return false; // 若PCB中已经存在该作业，则不更新PCB
			}
		}
		PCBList.add(new PCBBit(jobId, 1,jobLength));// 直接为就绪态
		System.out.println("接受该作业,id为："+jobId);
		return true;
	}

	public void addPCBValue(int id, String value) { // 向内存添加值

	}
}

