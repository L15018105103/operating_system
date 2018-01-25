package mySystem.memory;

import java.util.ArrayList;

public class PCBBit {
	public int jobId=-1; // 作业id
	public int state = 0; // 作业进程状态（该属性平常设为0，为保留值，1为就绪态，2为运行态，3为阻塞态
	public int waitTime=0; //作业等待时间
	public double percentage=0.0; //作业完成进度
	public double jobLength; // 作业的长度
	public ArrayList<Integer> jobBlockList = new ArrayList<Integer>(); //记录作业所占的内存块的队列，执行时按该队列执行
	public int lastPCBIndex=0; //记录上一次运行的作业的PCB列表的下标
	
	public PCBBit(){
		
	}
	public PCBBit(int jobId,int state,double jobLength){
		this.jobId=jobId;
		this.state=state;
		this.jobLength=jobLength-1;
	}
}
