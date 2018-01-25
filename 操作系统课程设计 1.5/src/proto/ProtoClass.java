package proto;

public interface ProtoClass {
	//Configure 3
	public long getTimePeriod();
	public int getBlockNum();
	public int getBlockSize();
	public int getAllotNum();
	public int getTimeSlice();
	//FileMessage 0
	public int getId();
	public String getFileName();
	public String getFileValue();
	//MemoryMessage 4
	public String getRunningIndex();
	public int getReplaceBlock();
	public String getMemoryValue();
	//ProcessMessage 2
	public String getJobId();
	public String getState();
	public String getWaitTime();
	public String getPercentage();
	//Message
	public String getMessage();
	//Timer 1
	public long getCurrentTime();
	//ResultMessage 5
	public String getResult();
	//DeviceMessage 6
	public String getDeviceName();
	public String getUsedjobId();
	public String getWaitingJobId();
	public String getUseTimeSlice();
}
