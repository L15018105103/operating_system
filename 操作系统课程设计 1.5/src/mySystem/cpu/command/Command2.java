package mySystem.cpu.command;

import io.netty.channel.Channel;
import main.Main;
import mySystem.MainThread;
import proto.ProtoClass;

/**
 * 通知服务器向前端发送时钟、内存信息、PCB信息、左下角右下角信息
 * @author a7840
 * 接收命令的格式：1,2+空字符
 * 调用该方法后，该方法设置ProcessThread的incoming为当前的incoming，同时设置各种标记为true，如sendTime等
 */
public class Command2 implements Command{
	@Override
	public void excute(Channel incoming, ProtoClass receiveObject) { //传入的realProto为不带头的二进制数组
		int safeCount=0; //安全计数器
		while(!Main.systemFlag){ //等待标记为true
			if(safeCount>100){
				return;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				safeCount++;
			}
		}
		Main.mySystem.setChannel1(incoming);
		Main.systemFlag=false;
		Main.mySystem.mainThread=new MainThread();
		Main.mySystem.mainThread.start();
	}
}
