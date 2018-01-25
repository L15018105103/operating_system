package typeFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import proto.ProtoClass;
import proto.ResultMessageProto.ResultMessage;
/**
 * 时钟类
 * 
 */
public class Type5 extends Type{
	//TODO 
	@Override
	public Object create(String...str) { //创建该类的对象
		ResultMessage.Builder builder= ResultMessage.newBuilder();
        builder.setResult(str[0]);
        return builder.build();
	}
	
	@Override
	public ProtoClass decodeExcute(byte[] buffer) { //传入protobuf编码的二进制数组，进行解码
		try {
			return ResultMessage.parseFrom(buffer);  //解码，返回一个解码后得到的对象
		} catch (InvalidProtocolBufferException e) {
			return null; //若解码遇到异常，则返回空对象
		}
	}

	@Override
	public byte[] encodeExcute(Object req) {
		byte[] msg=((ResultMessage) req).toByteArray(); //编码成二进制
		return msg;
	}

	@Override
	public ResultMessage create() { //创建一个空的类对象
		ResultMessage.Builder builder= ResultMessage.newBuilder();
		return builder.build();
	}
}
