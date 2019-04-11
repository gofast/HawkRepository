package com.jt.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
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
	    JSONObject json = this.getJsonObject(message);
	    if(json==null){
	    	ctx.writeAndFlush("json format exception");		
	    	return ;
	    }
	    String result = this.loadService(json.getString("classURL"),json.getString("methodName"));
	    ctx.writeAndFlush(result);		
		GroupFactory.validateChannelHandlerContext(message.substring(0, 6), ctx);
		boolean res = GroupFactory.getChannelHandlerContext(message.substring(0, 6), message.substring(7));
		if(!res){
			ctx.writeAndFlush("�����쳣������ʧ�ܣ�msg=");			
		}
    }
	
	private JSONObject getJsonObject(String text){
		try{
			
			return  JSONObject.parseObject(text);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	    
    //��⵽�������ӣ�����
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
       //�������һЩ�Ͽ����ӵĴ���   
    	System.out.println("server--------------------------------------------------------------------");
    	System.out.println("server-userEventTriggered-msg:"+evt);
    	System.out.println("  ");
    }
    
    private String loadService(String classURL,String methodName){
    	try {
			Class clazz = Class.forName("com.jt.rpc.server.provider.MyService");
			Object obj = clazz.newInstance();
			Method method = clazz.getDeclaredMethod(methodName, null);
			Object result = method.invoke(obj, null);
			return (String) result; 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "";
    }
    
    
    public static void main(String[] args) {
		/*String message=loadService(null); 
		System.out.println(message);*/
				
	}
}
