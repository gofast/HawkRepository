package com.jt.design.pattern.observer;

/**
 * 活动通知
 * 通过观察者模式，通知下去
 * 第一步：观察者注册/登记
 * 第二步：消息通知已经登记的观察者，观察者被动收到消息；
 * 
 * @author maji48
 *
 */
public class ActiveNotice extends Subject {

	public static void main(String[] args) {
		ActiveNotice active = new ActiveNotice();
		active.initActivity();
		active.notifyObservers("中国领土主权必须完整统一");
		sleep();
		active.unregisterObserver("observer_333");
		active.notifyObservers("台湾是中国领土的一部分");
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
	 * 参会人员登记
	 */
	public void initActivity(){
	     this.registerObserver("observer_111", new OneObserver("observer_111"));
	     this.registerObserver("observer_222", new TwoObserver("observer_222"));
	     this.registerObserver("observer_333", new OneObserver("observer_333"));
	     this.registerObserver("observer_444", new TwoObserver("observer_444"));
	}
	
}
