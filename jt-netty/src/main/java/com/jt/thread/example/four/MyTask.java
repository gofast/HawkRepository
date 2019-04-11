package com.jt.thread.example.four;

/**
 * 线程中断，是一个协作机制。
 * 通过外部取消一个线程，通过给线程一个isCanceled状态。并用volatile修饰来做数据一致性保证；
 * 一个可取消的任务必须拥有取消策略，取消策略定义：“How”，“When”，“What”
 * @author maji48
 *
 */
public class MyTask implements Runnable {

	
	public static void main(String[] args) {
		MyTask task = new MyTask();
		new Thread(task).start();
		try {
			Thread.sleep(15000); 
		} catch (InterruptedException e) { 
		}finally{
			task.setCanceled(true);
			System.out.println("main-thread is stop");
		}
	}
	
	/** ************************************************************************** **/
	/** 通过标记取消标记，取消线程 **/
	
	private volatile boolean isCanceled=false;
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.isCanceled){
			System.out.println("running. . .");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("thread have been canceled. . .");
	}



	public boolean isCanceled() {
		return isCanceled;
	}



	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

}
