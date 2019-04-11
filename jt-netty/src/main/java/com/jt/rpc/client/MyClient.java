package com.jt.rpc.client;

import com.jt.app.server.client.FirstClient;
import com.jt.app.server.client.MyChannelHandler;
import com.jt.app.server.client.TaskPoolFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyClient {

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
			f = bootstrap.connect("127.0.0.1", MyClient.port).sync();
			if(f.isSuccess()){
				System.out.println("TCP�ͻ������ӷ������ɹ�---------------");
			}
			int index=0;
			while(true){
				index++;
				System.out.print("client��sleeping��");
				for(int i=0;i<6;i++){
					try {
						Thread.sleep(2000);
						System.out.print(".");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}  
				
				String msag="{\"classURL\":\"com.jt.rpc.server.provider.MyService\",\"methodName\":\"queryName\"}";
				if(index%2==0){
					msag="{\"classURL\":\"com.jt.rpc.server.provider.MyService\",\"methodName\":\"queryTime\"}";
				}
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
		new MyClient().init();
	}
     
}
