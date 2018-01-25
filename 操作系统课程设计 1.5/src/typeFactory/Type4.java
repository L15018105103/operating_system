package typeFactory;

import com.google.protobuf.InvalidProtocolBufferException;

import proto.MemoryMessageProto.MemoryMessage;
import proto.ProtoClass;
/**
 * 内存类
 * 
 */
public class Type4 extends Type{
	//TODO 
	@Override
	public Object create(String...str) { //创建该类的对象
		MemoryMessage.Builder builder= MemoryMessage.newBuilder();
        builder.setRunningIndex(str[0]); //进程ID
        builder.setReplaceBlock(Integer.valueOf(str[1])); //进程状态
        builder.setMemoryValue(str[2]); //进程等待时间
        return builder.build();
	}
	
	@Override
	public ProtoClass decodeExcute(byte[] buffer) { //传入protobuf编码的二进制数组，进行解码
		try {
			return MemoryMessage.parseFrom(buffer);  //解码，返回一个解码后得到的对象
		} catch (InvalidProtocolBufferException e) {
			return null; //若解码遇到异常，则返回空对象
		}
	}

	@Override
	public byte[] encodeExcute(Object req) {
		byte[] msg=((MemoryMessage) req).toByteArray(); //编码成二进制
		return msg;
	}

	@Override
	public MemoryMessage create() { //创建一个空的类对象
		MemoryMessage.Builder builder= MemoryMessage.newBuilder();
		return builder.build();
	}
}
