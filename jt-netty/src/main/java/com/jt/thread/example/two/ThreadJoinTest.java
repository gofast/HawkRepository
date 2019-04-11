package com.jt.thread.example.two;

import java.util.concurrent.CountDownLatch;

public class ThreadJoinTest {

	public static void main(String[] args) {
		testJoin();
		CountDownLatch latch = new CountDownLatch(2);
	}
	
	/**
	 * ������T1��T2��T3�����̣߳���������֤T2��T1ִ�����ִ�У�T3��T2ִ�����ִ�У�
	 * thread.Join��ָ�����̼߳��뵽��ǰ�̣߳����Խ���������ִ�е��̺߳ϲ�Ϊ˳��ִ�е��̡߳� 
	 * �������߳�B�е������߳�A��Join()������ֱ���߳�Aִ����Ϻ󣬲Ż����ִ���߳�B�� 
	 * ��Ҫ�������˽⣬���鿴һ��join��Դ�룬Ҳ�ܼ򵥵ģ�ʹ��wait����ʵ�ֵġ�
	 * t.join(); //����join�������ȴ��߳�tִ����� 
	 * t.join(1000); //�ȴ� t �̣߳��ȴ�ʱ����1000���롣
	 */
	public static void testJoin(){
		Thread t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					System.out.println("1111:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					t1.join(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<10;i++){
					System.out.println("2222:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t3= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					t2.join(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<10;i++){
					System.out.println("3333:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
	
	public static void testThread(){
		Thread t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					System.out.println("1111:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					System.out.println("2222:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t3= new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					System.out.println("3333:"+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
}
