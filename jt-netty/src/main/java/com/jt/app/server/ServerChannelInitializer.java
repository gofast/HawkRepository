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
		// �����������
		channelPipeline.addLast("idleStateHandler", new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
		 //��ӱ������
		channelPipeline.addLast(new StringDecoder(), new StringEncoder());
		//�Զ���Hadler
		channelPipeline.addLast("handler",new TCPServerHandler());
        /*//�Զ���Hander,�����ڴ����ʱ������������IO�����߳�
		channelPipeline.addLast(group,"BussinessHandler",new BussinessHandler());*/
	}

}
