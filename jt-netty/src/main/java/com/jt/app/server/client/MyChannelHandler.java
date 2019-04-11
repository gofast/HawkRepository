package com.jt.app.server.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyChannelHandler extends ChannelInboundHandlerAdapter{
	
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) { 
    	System.out.println("server--------------------------------------------------------------------");
        ctx.writeAndFlush("---");
    }

 
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	TaskPoolFactory.setCtx(ctx);
    	System.out.println("");
    	System.out.println("client--------------------------------------------------------------------"); 
    	System.out.println("client-receive:"+msg);
    	
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
