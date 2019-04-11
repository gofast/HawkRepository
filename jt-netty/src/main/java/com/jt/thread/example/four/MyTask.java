package com.jt.thread.example.four;

/**
 * �߳��жϣ���һ��Э�����ơ�
 * ͨ���ⲿȡ��һ���̣߳�ͨ�����߳�һ��isCanceled״̬������volatile������������һ���Ա�֤��
 * һ����ȡ�����������ӵ��ȡ�����ԣ�ȡ�����Զ��壺��How������When������What��
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
	/** ͨ�����ȡ����ǣ�ȡ���߳� **/
	
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
