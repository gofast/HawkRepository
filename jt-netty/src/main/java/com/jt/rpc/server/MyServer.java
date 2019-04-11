package com.jt.rpc.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
	//����Accept�����¼����̣߳������߳�������Ϊ1���ɣ�netty���������¼�Ĭ��Ϊ���̣߳��������÷����˷�cpu��Դ
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    //����hadnler�Ĺ����̣߳���ʵҲ���Ǵ���IO��д ���߳�����Ĭ��Ϊ CPU ����������2
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final static int port = 8888;
     
    
    public void init(){
    	//����ServerBootstrapʵ��
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
      //���ý�Ҫ��ʵ������ServerChannel��
        serverBootstrap.channel(NioServerSocketChannel.class); 
      //��ServerChannelInitializer�г�ʼ��ChannelPipeline������������ӵ�serverBootstrap��
        serverBootstrap.childHandler(new ServerChannelInitializer());
        //��ʶ���������������߳�ȫ��ʱ��������ʱ���������������ֵ�����Ķ��е���󳤶�
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // �Ƿ������������������
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);    
        //�󶨶˿ں󣬿�������
        ChannelFuture channelFuture;
		try {
			channelFuture = serverBootstrap.bind(MyServer.port).sync();
			if(channelFuture.isSuccess()){
				System.out.println("TCP�������� �ɹ�---------------");
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
