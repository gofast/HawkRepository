package com.jt.rpc.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
	//处理Accept连接事件的线程，这里线程数设置为1即可，netty处理链接事件默认为单线程，过度设置反而浪费cpu资源
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    //处理hadnler的工作线程，其实也就是处理IO读写 。线程数据默认为 CPU 核心数乘以2
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final static int port = 8888;
     
    
    public void init(){
    	//创建ServerBootstrap实例
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
      //设置将要被实例化的ServerChannel类
        serverBootstrap.channel(NioServerSocketChannel.class); 
      //在ServerChannelInitializer中初始化ChannelPipeline责任链，并添加到serverBootstrap中
        serverBootstrap.childHandler(new ServerChannelInitializer());
        //标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // 是否启用心跳保活机机制
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);    
        //绑定端口后，开启监听
        ChannelFuture channelFuture;
		try {
			channelFuture = serverBootstrap.bind(MyServer.port).sync();
			if(channelFuture.isSuccess()){
				System.out.println("TCP服务启动 成功---------------");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(bossGroup.isShutdown()){ 
				bossGroup.shutdownGracefully(); 
			}
			if(workerGroup.isShutdown()){ 
				workerGroup.shutdownGracefully(); 
			}
		}
    }
    
    public static void main(String[] args) {
		new MyServer().init();
	}
}
