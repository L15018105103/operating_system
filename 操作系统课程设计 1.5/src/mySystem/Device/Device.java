package mySystem.Device;

/**
 * 设备类
 * 
 * @author a7840
 *
 */
public class Device {
	public String deviceName; // 设备名字
	public String usedJobId=""; // 正在占用设备的作业ID
	public String waitingJobId=""; // 等待的进程
	public int useTimeSlice; // 设备占用的时间片
	public int state; // 设备状态，0表示空闲，1表示占用
	public int usedTime; // 占用时间

	// 初始化设备的方法
	public Device(String deviceName) { // 设备名字
		this.deviceName = deviceName;
	}
}
