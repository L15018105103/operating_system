package typeFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import proto.ProtoClass;
import proto.DeviceMessageProto.DeviceMessage;
/**
 * 设备信息的类
 * 
 */
public class Type6 extends Type{
	//TODO 
	@Override
	public Object create(String...str) { //创建该类的对象
		DeviceMessage.Builder builder= DeviceMessage.newBuilder();
        builder.setDeviceName(str[0]);
        builder.setState(str[1]);
        builder.setUsedjobId(str[2]);
        builder.setWaitingJobId(str[3]);
        builder.setUseTimeSlice(str[4]);
        return builder.build();
	}
	
	@Override
	public ProtoClass decodeExcute(byte[] buffer) { //传入protobuf编码的二进制数组，进行解码
		try {
			return DeviceMessage.parseFrom(buffer);  //解码，返回一个解码后得到的对象
		} catch (InvalidProtocolBufferException e) {
			return null; //若解码遇到异常，则返回空对象
		}
	}

	@Override
	public byte[] encodeExcute(Object req) {
		byte[] msg=((DeviceMessage) req).toByteArray(); //编码成二进制
		return msg;
	}

	@Override
	public DeviceMessage create() { //创建一个空的类对象
		DeviceMessage.Builder builder= DeviceMessage.newBuilder();
		return builder.build();
	}
}
