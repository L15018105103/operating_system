package typeFactory;

import proto.ProtoClass;

public abstract class Type {
	public abstract Object create();
	public abstract Object create(String...str1); //带有多个String属性的类的create方法
	public abstract ProtoClass decodeExcute(byte[] protoBuffer); //传入protobuf二进制数组，执行解码，解码后返回一个protobuf父类对象
	public abstract byte[] encodeExcute(Object a); //进行编码
}
