package mySystem.memory;

import java.util.ArrayList;

//内存的块
public class Block {
	public int jobId; //作业ID
	public int blockNo; //块号
	public boolean canBeCoveredFlag=true; //标记该块能够被覆盖
	public ArrayList<MemoryBit> memoryList = new ArrayList<MemoryBit>(); //块内维护一个私有的内存列表
	private MemoryBit temp; //初始化时用于存储内存的单元
	public Block(int blockNum,int blockSize){ //传入块的大小，进行块的初始化
		this.blockNo=blockNum; //设置块号
		for(int i=0;i<blockSize;i++){
			temp=new MemoryBit();
			memoryList.add(temp);
		}
	}
	public MemoryBit addBuffer(ArrayList<String> buffer){ //内存块的缓冲区
		for(int i=0;i<buffer.size();i++){
			temp=new MemoryBit();
			memoryList.add(temp);
		}
		return temp;
	}
}
