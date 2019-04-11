package com.jt.design.pattern.observer;

public class OneObserver implements Observer {

	private String name;
	
	public OneObserver(String name){
		this.name=name;
	}
	
	@Override
	public void notice(String message) {
		System.out.println("--- # �۲���["+name+"] �յ���Ϣ:"+message+" \n"); 
	}

}
