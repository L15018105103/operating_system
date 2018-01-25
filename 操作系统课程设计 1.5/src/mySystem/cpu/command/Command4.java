package mySystem.cpu.command;

import io.netty.channel.Channel;
import main.Main;
import proto.ProtoClass;

/**
 * 设备管理 用户发来6 4，激活设备管理端口
 * @author a7840
 * 
 */
public class Command4 implements Command{
	@Override
	public void excute(Channel incoming, ProtoClass receiveObject) { //传入的realProto为不带头的二进制数组
		Main.mySystem.setChannel2(incoming);
	}
}
