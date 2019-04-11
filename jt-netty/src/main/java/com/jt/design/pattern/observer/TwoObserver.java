package com.jt.design.pattern.observer;

public class TwoObserver implements Observer {

	private String name;
	
	public TwoObserver(String name){
		this.name=name;
	}
	
	@Override
	public void notice(String message) {
		System.out.println("--- * --------------------------------------------");
		System.out.println("--- * 观察者["+name+"] 收到消息:"+message+" \n"); 
	}

}
