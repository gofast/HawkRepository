package com.jt.thread.example.four;

/**
 * 线程中断，是一个协作机制。
 * 通过外部取消一个线程，通过给线程一个isCanceled状态。并用volatile修饰来做数据一致性保证；
 * 一个可取消的任务必须拥有取消策略，取消策略定义：“How”，“When”，“What”
 * @author maji48
 *
 */
public class CancelTask extends Thread {

	
	public static void main(String[] args) {
		CancelTask task = new CancelTask();
		task.start();
		try {
			Thread.sleep(5000); 
		} catch (InterruptedException e) { 
		}finally{
			task.cancel();
			System.out.println("main-thread is stop");
		}
	}
	
	/** ************************************************************************** **/
	/** 通过中断方式（interrupt），取消线程: 不仅可以中断sleep 
	 * 与取消状态对比，中断方式更迅速 **/
	 
	
	public void run() {
		// TODO Auto-generated method stub 
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("running. . ."); 
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		} 
		System.out.println("thread have been canceled. . .");
	}



	public void cancel() {
		interrupt(); 
	}

 

}
