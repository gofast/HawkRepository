package com.jt.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

public class GroupFactory {

	private static Map<String,ChannelHandlerContext> channelCacherMap = new ConcurrentHashMap<String,ChannelHandlerContext>();
	
	private static final Object SYNCH = new Object();
	
	
	public static void getChannelHandlerContext(){
		synchronized (SYNCH) { 
			String list = channelCacherMap.keySet().toString();
			for(Map.Entry<String,ChannelHandlerContext> cacheMap:channelCacherMap.entrySet()){
				cacheMap.getValue().writeAndFlush("{\"onlines\":\""+list+"\"}");
			}
		}
	}
	
	
	public static void validateChannelHandlerContext(String key,ChannelHandlerContext channel){
		synchronized (SYNCH) { 
			if(null ==  channelCacherMap.get(key)){
				channelCacherMap.put(key, channel);
			}
		}
	}
	
	
	public static boolean getChannelHandlerContext(String key,String msg){
		synchronized (SYNCH) {  
			if(null ==  channelCacherMap.get(key)){
				return false;
			}
			for(Map.Entry<String,ChannelHandlerContext> cacheMap:channelCacherMap.entrySet()){
				if(!key.equals(cacheMap.getKey())){
					cacheMap.getValue().writeAndFlush("{\"{"+key+"}-say\":\""+msg+"\"}"); 
				}
			}
			return true;
		}
	}
	
}
