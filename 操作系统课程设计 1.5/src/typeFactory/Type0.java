package typeFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import proto.FileMessageProto.FileMessage;
import proto.ProtoClass;
/**
 * 第0个类的工厂
 * 发送内存内容的类
 */
public class Type0 extends Type{
	@Override
	public Object create(String...str) { //创建该类的对象
		FileMessage.Builder builder= FileMessage.newBuilder();
        builder.setFileValue(str[0]);
        return builder.build();
	}

	@Override
	public ProtoClass decodeExcute(byte[] buffer) { //传入protobuf编码的二进制数组，进行解码
		try {
			return FileMessage.parseFrom(buffer);  //解码，返回一个解码后得到的对象
		} catch (InvalidProtocolBufferException e) {
			return null; //若解码遇到异常，则返回空对象
		}
	}

	@Override
	public byte[] encodeExcute(Object req) { //编码
		byte[] msg=((FileMessage) req).toByteArray(); //编码成二进制
		return msg;
	}

	@Override
	public Object create() { //创建一个空的类对象
		FileMessage.Builder builder= FileMessage.newBuilder();
		return builder.build();
	}
}
