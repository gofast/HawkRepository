package com.jt.app.server.client;

import com.jt.app.server.JTMasterServer;
import com.jt.app.server.ServerChannelInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class FirstClient { 
    //����hadnler�Ĺ����̣߳���ʵҲ���Ǵ���IO��д ���߳�����Ĭ��Ϊ CPU ����������2
    private final EventLoopGroup worker = new NioEventLoopGroup();
    private final static int port = 8888;
     
    
    public void init(){
    	//����ServerBootstrapʵ��
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(worker).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast(new StringEncoder(), new StringDecoder());
                p.addLast(new MyChannelHandler());
            }
        });  
        // Start the client.
        ChannelFuture f;
		try {
			f = bootstrap.connect("127.0.0.1", FirstClient.port).sync();
			if(f.isSuccess()){
				System.out.println("TCP�ͻ������ӷ������ɹ�---------------");
			}
			while(true){
				
				System.out.print("client��sleeping��");
				for(int i=0;i<6;i++){
					try {
						Thread.sleep(2000);
						System.out.print(".");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}  
				String msag="{\"\":\"\",\"\":\"\"}";
				System.out.println("\nclient��sendMsg��"+msag);
				TaskPoolFactory.sendMessage(msag); 
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(worker.isShutdown()){ 
				worker.shutdownGracefully(); 
			}
		}
 
    }
    
    public static void main(String[] args) {
		new FirstClient().init();
	}
     
}
