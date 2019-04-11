package com.jt.app.server.client;


import io.netty.channel.ChannelHandlerContext;

public class TaskPoolFactory {
 
		private static ChannelHandlerContext ctx;
		private static final Object SYNCH= new Object();

		public static void sendMessage(String msg) {
			synchronized (SYNCH) {
				if(TaskPoolFactory.ctx!=null){
					TaskPoolFactory.ctx.writeAndFlush(msg);
				}
			}
		}

		public static void setCtx(ChannelHandlerContext ctx) {
			synchronized (SYNCH) {
				if(TaskPoolFactory.ctx==null){ 
					TaskPoolFactory.ctx = ctx;
				}
			}
		}
		
		
}
