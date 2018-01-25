package mySystem.cpu.command;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import main.Main;
import proto.ProtoClass;
import typeFactory.Type;

/**
 * 该类用于解析byte[]中的命令号：order[1]并执行命令，无需负责protobuf对象的反序列化
 * 该类的对象只需实例化一个，所以在程序开始时实例化成一个static对象即可
 * 
 * @author a7840
 *
 */
public class Order { // 该类用来获取命令内容、解析命令并调用相应的来执行命令。
	public CommandPipeLine commandList = new CommandPipeLine();
	private int maxNum = 10; // 总命令数量

	// 解析接收到的指令
	public void excute(Channel incoming, WebSocketFrame frame) {
		ByteBuf buf = frame.content();
		byte[] order = new byte[buf.readableBytes()]; // 获得收到的二进制数组
		buf.readBytes(order);
		if (order[1] < 0 || order[1] > maxNum) { // 检查命令号是否合法
			return; // 指令不合法就退出
		}
		byte[] realProto = new byte[order.length - 2];
		realProto = getReal(realProto, order); // 去掉两个头
		Type type =Main.type.getType(order[0]); //得到对象(注意：该类型对象只有一些方法，没有实质性的内容！)
		ProtoClass receiveObject=type.decodeExcute(realProto); //解码(解码后才有实质性的内容！)
		// 执行解释过后的命令，此处传入的为不带头的二进制数组
		commandList.commands.get(order[1]).excute(incoming, receiveObject); 
	}

	// 向服务器发送内容
	public static void send(Channel incoming, int head1, int head2, String... str) { // TODO 传入的内容可以是非字符串类型，有待扩展
		Type type =Main.type.getType(head1); //得到对象(注意：该类型对象只有一些方法，没有实质性的内容！)
		// 创建对象及编码
		byte[] msg = type.encodeExcute(type.create(str));
		// 加头
		byte[] msgCopy = new byte[msg.length + 2];
		Main.order.getFull((byte) head1, (byte) head2, msgCopy, msg); // 加头
		// 正式发
		ByteBuf encoded = incoming.alloc().buffer(msgCopy.length);
		encoded.writeBytes(msgCopy);
		incoming.writeAndFlush(new BinaryWebSocketFrame(encoded));
	}

	/*
	 * 
	 * @author a7840 System.arraycopy(Object src, int srcPos, Object dest, int
	 * destPos, int length); src:源数组； srcPos:源数组要复制的起始位置；dest:目的数组；
	 * destPos:目的数组放置的起始位置； length:复制的长度。
	 */
	// 将真实的protobuf码加上两个头
	public byte[] getFull(byte type, byte orderNumber, byte[] msgCopy, byte[] msg) { // msgCoppy的长度比msg多2
		System.arraycopy(msg, 0, msgCopy, 2, msg.length);
		msgCopy[0] = type;
		msgCopy[1] = orderNumber;
		return msgCopy;
	}

	// 加了头的protobuf还原,即去掉两个头
	public byte[] getReal(byte[] msgCopy, byte[] msg) { // msgCopy的长度比msg短2
		System.arraycopy(msg, 2, msgCopy, 0, msg.length - 2);
		return msgCopy;
	}
}
