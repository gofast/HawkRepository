package com.jt.app.server;

import com.jt.app.GroupFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TCPServerHandler extends ChannelInboundHandlerAdapter {
	
	
	public TCPServerHandler() {
	    
	}
	    
	    
	@Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
	       //�õ���������msg���ݣ���ʼ����
    	System.out.println("server--------------------------------------------------------------------");
		System.out.println("server-channelRead-msg:"+msg);
		System.out.println("  ");
		String message= (String) msg;
		if(message.length()<7){
			ctx.writeAndFlush("---");
			return ;
		}
		GroupFactory.validateChannelHandlerContext(message.substring(0, 6), ctx);
		boolean result = GroupFactory.getChannelHandlerContext(message.substring(0, 6), message.substring(7));
		if(!result){
			ctx.writeAndFlush("�����쳣������ʧ�ܣ�msg=");			
		}
    }
	    
    //��⵽�������ӣ�����
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
       //�������һЩ�Ͽ����ӵĴ���   
    	System.out.println("server--------------------------------------------------------------------");
    	System.out.println("server-userEventTriggered-msg:"+evt);
    	System.out.println("  ");
    }
    
    public static void main(String[] args) {
		String message="asdfghjkl";
		System.out.println(message.substring(0, 5));
		System.out.println(message.substring(5));
				
	}
}
