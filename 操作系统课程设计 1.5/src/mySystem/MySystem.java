package mySystem;

import java.util.ArrayList;

import io.netty.channel.Channel;
import mySystem.cpu.Register;
import mySystem.cpu.timeer.Clock;
import mySystem.memory.Memory;
import mySystem.Device.*;

/**
 * 
 * @author a7840 虚拟操作系统的主线程类
 */

public class MySystem {
	public Memory memory;
	public Clock clock;
	ArrayList<Device> devices = new ArrayList<Device>(); //存储设备
	public MainThread mainThread; // 主线程
	public Channel[] incoming=new Channel[2]; // 与前端保持的长连接
	public int runIndex = -1; // 记录上一次运行的PCB元素的下标
	public int allotNum; // 每个作业分配的页表数
	public Register register = new Register(); // 初始化四个寄存器

	public MySystem(long timePeriod, int blockNum, int blockSize, int allotNum, int timeSlice) {
		//初始化内存
		memory = new Memory(blockNum, blockSize); // blockNum,blockSize
		//初始化时钟
		clock = new Clock(timePeriod, timeSlice); // clockPeriod,timeSlice
		//初始化分配的页表数的参数
		this.allotNum = allotNum;
		//初始化device
		devices.add(new Device("A1"));
		devices.add(new Device("A2"));
		devices.add(new Device("B1"));
		devices.add(new Device("B2"));
		devices.add(new Device("B3"));
		devices.add(new Device("C1"));
		devices.add(new Device("C2"));
		devices.add(new Device("C3"));
	}

	public void setChannel1(Channel incoming) {
		this.incoming[0] = incoming;
	}
	
	public void setChannel2(Channel incoming) {
		this.incoming[1] = incoming;
	}
}
