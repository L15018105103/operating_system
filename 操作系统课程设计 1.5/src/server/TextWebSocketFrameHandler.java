package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import main.Main;

/**
 * 处理TextWebSocketFrame
 * 
 * @author 
 */
public class TextWebSocketFrameHandler extends ChannelInboundHandlerAdapter {

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	//private WebSocketServerHandshaker handshaker; //？
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object frame) throws Exception {
		Channel incoming = ctx.channel();
		if(frame instanceof BinaryWebSocketFrame) { //如果接收到的是二进制数据 
			Main.order.excute(incoming,(WebSocketFrame) frame);	
        }
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		// channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " +
		// incoming.remoteAddress() + " 加入"));

		channels.add(incoming);
		System.out.println("Client:" + incoming.remoteAddress() + "加入");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		// channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " +
		// incoming.remoteAddress() + " 离开"));

		System.out.println("Client:" + incoming.remoteAddress() + "离开");

		// A closed Channel is automatically removed from ChannelGroup,
		// so there is no need to do "channels.remove(ctx.channel());"
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
