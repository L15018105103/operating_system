package main;

import mySystem.MySystem;
import mySystem.cpu.command.Order;
import server.WebsocketChatServer;
import typeFactory.TypeFactory;
/**
 * 
 * @author a7840
 * 该类是项目的主类
 */
public class Main {
	public static Order order=new Order(); //实例化命令对象
	public static boolean systemFlag; //是否已开机
	public static MySystem mySystem;
	//public static MySystem mySystem=new MySystem(1000,4,4,1L,1); //TODO 当收到3 3指令时才实例化该系统
	public static TypeFactory type=new TypeFactory(); //实例化类型工厂
	public static void main(String[] args) throws Exception {
		new WebsocketChatServer(7778).run(); //启动服务器
	}
	//启动系统
	public static void openSystem(long timePeriod, int blockNum, int blockSize, int AllotNum, int timeSlice){
		mySystem=new MySystem(timePeriod,blockNum,blockSize,AllotNum,timeSlice);
		systemFlag=true;
	}
}
