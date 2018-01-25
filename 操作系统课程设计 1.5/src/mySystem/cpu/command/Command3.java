package mySystem.cpu.command;

import io.netty.channel.Channel;
import main.Main;
import proto.ProtoClass;

/**
 * 开机配置，客户端发来3 3 配置信息，即刻开机，完成属性设置以及启动各个线程
 * @author a7840
 * 
 */
public class Command3 implements Command{
	@Override
	public void excute(Channel incoming, ProtoClass receiveObject) { //传入的realProto为不带头的二进制数组
		System.out.println("块数="+receiveObject.getBlockNum());
		Main.openSystem(receiveObject.getTimePeriod(),receiveObject.getBlockNum(),receiveObject.getBlockSize(),receiveObject.getAllotNum(),receiveObject.getTimeSlice()); //启动系统
		System.out.println("已开机，系统时钟周期="+Main.mySystem.clock.getPeriod());
	}
}
