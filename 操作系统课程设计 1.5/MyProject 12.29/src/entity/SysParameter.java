package entity;

public class SysParameter {
	private String clock;
	private String blockNumber;
	private String blockSize;
	private String hardDisk;
	private String pageCount;
	private String timeSlice;
	private String pageBuffer;
	
	public SysParameter(String clock, String blockNumber,String blockSize, String hardDisk,
			String pageCount, String timeSlice, String pageBuffer)
	{
		this.clock = clock;
		this.blockNumber =blockNumber;
		this.blockSize = blockSize;
		this.hardDisk = hardDisk;
		this.pageCount = pageCount;
		this.timeSlice = timeSlice;
		this.pageBuffer = pageBuffer;
		
	}
	public SysParameter()
	{
		
	}
	
	public String getClock()
	{
		return clock;
	}
	public String getBlockNumber()
	{
		return blockNumber;
	}
	public String getBlockSize()
	{
		return blockSize;
	}
	public String getHardDisk()
	{
		return hardDisk;
	}
	public String getPageCount()
	{
		return pageCount;
	}
	public String getTimeSlice()
	{
		return timeSlice;
	}
	public String getPageBuffer()
	{
		return pageBuffer;
	}
	public void setClock(String clock)
	{
		this.clock = clock;
	}
	public void setBlockNumber(String blockNumber)
	{
		this.blockNumber = blockNumber;
	}
	public void setBlockSize(String blockSize)
	{
		this.blockSize = blockSize;
	}
	public void setHardDisk(String hardDisk)
	{
		this.hardDisk = hardDisk;
	}
	public void setPageCount(String pageCount)
	{
		this.pageCount = pageCount;
	}
	public void setTimeSlice(String timeSlice)
	{
		this.timeSlice = timeSlice;
	}
    public void setPageBuffer (String pageBuffer)
    {
    	this.pageBuffer = pageBuffer;
    }
}
