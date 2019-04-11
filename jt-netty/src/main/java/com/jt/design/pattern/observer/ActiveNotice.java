package com.jt.design.pattern.observer;

/**
 * �֪ͨ
 * ͨ���۲���ģʽ��֪ͨ��ȥ
 * ��һ�����۲���ע��/�Ǽ�
 * �ڶ�������Ϣ֪ͨ�Ѿ��ǼǵĹ۲��ߣ��۲��߱����յ���Ϣ��
 * 
 * @author maji48
 *
 */
public class ActiveNotice extends Subject {

	public static void main(String[] args) {
		ActiveNotice active = new ActiveNotice();
		active.initActivity();
		active.notifyObservers("�й�������Ȩ��������ͳһ");
		sleep();
		active.unregisterObserver("observer_333");
		active.notifyObservers("̨�����й�������һ����");
	}
	
	public static void sleep(){
		try {
			Thread.sleep(2000);
			System.out.println("----------------------------------------------------------------------------------------");
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	
	/**
	 * �λ���Ա�Ǽ�
	 */
	public void initActivity(){
	     this.registerObserver("observer_111", new OneObserver("observer_111"));
	     this.registerObserver("observer_222", new TwoObserver("observer_222"));
	     this.registerObserver("observer_333", new OneObserver("observer_333"));
	     this.registerObserver("observer_444", new TwoObserver("observer_444"));
	}
	
}
