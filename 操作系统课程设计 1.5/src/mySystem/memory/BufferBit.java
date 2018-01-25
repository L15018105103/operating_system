package mySystem.memory;

/**
 * 页表缓冲区的单元
 * @author a7840
 *
 */
public class BufferBit {
	int jobId; //作业的id
	double jobLength; // 作业的总长度
	String value; //作业的指令内容
	public BufferBit(int jobId,String value,double jobLength){
		this.jobId=jobId;
		this.value=value;
		this.jobLength=jobLength;
	}
}
