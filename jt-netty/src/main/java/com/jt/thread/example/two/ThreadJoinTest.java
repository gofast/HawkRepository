package com.jt.thread.example.two;

import java.util.concurrent.CountDownLatch;

public class ThreadJoinTest {

	public static void main(String[] args) {
		testJoin();
		CountDownLatch latch = new CountDownLatch(2);
	}
	
	/**
	 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
	 * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。 
	 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。 
	 * 想要更深入了解，建议看一下join的源码，也很简单的，使用wait方法实现的。
	 * t.join(); //调用join方法，等待线程t执行完毕 
	 * t.join(1000); //等待 t 线程，等待时间是1000毫秒。
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
