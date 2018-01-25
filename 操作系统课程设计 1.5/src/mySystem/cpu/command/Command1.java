package mySystem.cpu.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import io.netty.channel.Channel;
import main.Main;
import proto.ProtoClass;

/**
 * 客户端点作业的“执行”按钮，向后台发送0 1时，后台执行该命令
 * @author a7840
 * 用户点作业运行按钮时即触发此命令。
 * 该命令主要负责根据传来的文件名->读取相应的文件->写入缓冲区
 */
public class Command1 implements Command{
	@Override
	public void excute(Channel incoming, ProtoClass receiveObject) { //传入的realProto为不带头的二进制数组
		File file = new File("disk/"+receiveObject.getFileName());
		StringBuilder fileWords = new StringBuilder();
		double length=0.0;
		//读第一次：计算作业长度
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            	while((br.readLine())!=null){//使用readLine方法，一次读一行
            		length++;
                }
                br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //读第二次
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            	while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            		length++;
            		fileWords.append(s+"\n");
                	//把文件的行（一条指令）写入内存的某个块
                	Main.mySystem.memory.addBuffer(receiveObject.getId(), s.toString(),length); //将作业id和指令写入内存的页表缓冲区
                }
                br.close();
            //System.out.println(receiveObject.getId());
            //System.out.println(fileWords);
        }catch(Exception e){
            e.printStackTrace();
        }
	}
}
