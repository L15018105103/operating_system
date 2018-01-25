package mySystem.cpu.timeer;

public class Clock {
	private long systemTime;
	private long period=1; //时钟周期
	public int timeSlice; // 时间片大小，可以直接访问
	private long firstTime=(long)(System.currentTimeMillis()/this.period)*this.period; //起始时间
	
	public Clock(long period,int timeSlice){
		this.period=period;
		this.timeSlice=timeSlice;
	}
	public void setPeriod(long period){ //设置时钟周期
		this.period=period;
	}
	public long getTime(){ //获取系统时钟
		this.systemTime=((long)(System.currentTimeMillis()/this.period)*this.period-this.firstTime)/this.period%60; //服务器系统时间/虚拟操作系统时钟周期*虚拟操作系统时钟周期-起始时间
		return this.systemTime;
	}
	public long getPeriod(){
		return this.period;
	}
}
