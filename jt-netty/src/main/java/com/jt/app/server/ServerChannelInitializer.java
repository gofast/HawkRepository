package com.jt.app.server;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline channelPipeline = arg0.pipeline();
		// 添加心跳机制
		channelPipeline.addLast("idleStateHandler", new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
		 //添加编解码器
		channelPipeline.addLast(new StringDecoder(), new StringEncoder());
		//自定义Hadler
		channelPipeline.addLast("handler",new TCPServerHandler());
        /*//自定义Hander,可用于处理耗时操作，不阻塞IO处理线程
		channelPipeline.addLast(group,"BussinessHandler",new BussinessHandler());*/
	}

}
