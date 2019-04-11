package com.jt.design.pattern.observer;

public class TwoObserver implements Observer {

	private String name;
	
	public TwoObserver(String name){
		this.name=name;
	}
	
	@Override
	public void notice(String message) {
		System.out.println("--- * --------------------------------------------");
		System.out.println("--- * �۲���["+name+"] �յ���Ϣ:"+message+" \n"); 
	}

}
