package com.jt.thread.example.four;

/**
 * �߳��жϣ���һ��Э�����ơ�
 * ͨ���ⲿȡ��һ���̣߳�ͨ�����߳�һ��isCanceled״̬������volatile������������һ���Ա�֤��
 * һ����ȡ�����������ӵ��ȡ�����ԣ�ȡ�����Զ��壺��How������When������What��
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
	/** ͨ���жϷ�ʽ��interrupt����ȡ���߳�: ���������ж�sleep 
	 * ��ȡ��״̬�Աȣ��жϷ�ʽ��Ѹ�� **/
	 
	
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
