package typeFactory;

import com.google.protobuf.InvalidProtocolBufferException;

import proto.ProcessMessageProto.ProcessMessage;
import proto.ProtoClass;
/**
 * process类
 * 
 */
public class Type2 extends Type{
	//TODO 
	@Override
	public Object create(String...str) { //创建该类的对象
		ProcessMessage.Builder builder= ProcessMessage.newBuilder();
        builder.setJobId(str[0]); //进程ID
        builder.setState(str[1]); //进程状态
        builder.setWaitTime(str[2]); //进程等待时间
        builder.setPercentage(str[3]); //进程等待时间
        return builder.build();
	}
	
	@Override
	public ProtoClass decodeExcute(byte[] buffer) { //传入protobuf编码的二进制数组，进行解码
		try {
			return ProcessMessage.parseFrom(buffer);  //解码，返回一个解码后得到的对象
		} catch (InvalidProtocolBufferException e) {
			return null; //若解码遇到异常，则返回空对象
		}
	}

	@Override
	public byte[] encodeExcute(Object req) {
		byte[] msg=((ProcessMessage) req).toByteArray(); //编码成二进制
		return msg;
	}

	@Override
	public ProcessMessage create() { //创建一个空的类对象
		ProcessMessage.Builder builder= ProcessMessage.newBuilder();
		return builder.build();
	}
}
