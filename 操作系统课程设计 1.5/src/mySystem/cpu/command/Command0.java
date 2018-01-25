package mySystem.cpu.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.netty.channel.Channel;
import proto.ProtoClass;

/**
 * 写文件的命令
 * @author a7840
 * 该命令主要负责根据传来的文件名和内容，创建相应的文件
 */
public class Command0 implements Command{
	@Override
	public void excute(Channel incoming, ProtoClass receiveObject) { //传入的realProto为不带头的二进制数组
		System.out.println(receiveObject.getFileName());
		File file = new File("disk/"+receiveObject.getFileName());
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile(); //创建文件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 向文件写入内容(输出流)  
        String str =receiveObject.getFileValue();
        byte bt[] = new byte[1024];
        bt = str.getBytes();  
        try {  
            FileOutputStream in = new FileOutputStream(file);  
            try {
                in.write(bt, 0, bt.length);
                in.close();  
                // boolean success=true;  
                // System.out.println("写入文件成功");  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
	}
}
