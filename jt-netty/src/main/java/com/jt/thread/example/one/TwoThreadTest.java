package com.jt.thread.example.one;

public class TwoThreadTest {

	public static void main(String[] args) {
	
		new TwoThreadTest().test2();
		
		
	}

	/** ******************************************************************************** */
	private volatile String object = null;
	/**����һ��
	 * Ҫ��1��д�����̣߳�һ���̴߳�ӡ1~52����һ���̴߳�ӡA~Z����ӡ˳����12A34B...5152Z��
	 * Ҫ��2��ʹ��lambda���ʽ������������
	 */
	public void test(){ 
		Thread th1 = new Thread(()->{
			for(int i=1;i<=52;i=i+2){
				for(;;){
					if(object==null){
						System.out.print(i+""+(i+1));
						object=i+""+(i+1);
						break;
					}
				}
			}
		});
		Thread th2 = new Thread(()->{
			final int end='Z';
			final int start = 'A'; 
			for(int index = start;index<=end; index++){
				for(;;){
					if(object!=null){
						System.out.print((char)index);
						object=null;
						break;
					}
				}
			}
		});
		th1.start();
		th2.start();
		
	}
	

	/** ******************************************************************************** */
	private boolean flag = false;
	/**��������
	 * Ҫ��1��д�����̣߳�һ���̴߳�ӡ1~52����һ���̴߳�ӡA~Z����ӡ˳����12A34B...5152Z��
	 * Ҫ��2��ʹ��lambda���ʽ������������
	 * Ҫ��3��ͬ���ؼ��� synchronized
	 */
	public void test2(){ 
		
		new Thread(()->{
			for(int i=1;i<=52;i=i+2){
				this.print152(i+""+(i+1));
			}
		}).start();
		new Thread(()->{
			final int end='Z';
			final int start = 'A'; 
			for(int index = start;index<=end; index++){
				printAZ((char)index); 
			}
		}).start();
	}
	
	public synchronized void print152(String msg){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print(msg);   
		flag=true;
		this.notify();
	}
	
	public synchronized void printAZ(char index){
		if(flag==false){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print(index); 
		flag=false;
		this.notify();
	}

	/** ******************************************************************************** */
}
