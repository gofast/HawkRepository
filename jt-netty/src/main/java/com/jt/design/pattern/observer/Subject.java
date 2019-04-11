package com.jt.design.pattern.observer;
 
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; 

/**
 * 被观察者：可以理解成观察者们观察的目标
 * @author maji48
 *
 */
public abstract class Subject {

	private static final ConcurrentHashMap<String,Observer> observers = new ConcurrentHashMap<String,Observer>();
	private static final Object SYNCH = new Object();
	
	/**
	 * 注册观察者
	 * @param key
	 * @param observer
	 */
	public void registerObserver(String key,Observer observer){
		
		synchronized (SYNCH) {
			if(observers.containsKey(key)){
				System.out.println("--- 观察者[key="+key+" ] 已经被注册，新观察者注册失败 ----------");
			}else{
				observers.put(key, observer);
				System.out.println("--- 观察者[key="+key+" ] 注册成功 -----------------------");
			}
		}
	}
	
	/**
	 * 取消指定观察者的注册
	 * @param key
	 * @param observer
	 */
	public void unregisterObserver(String key){
		
		synchronized (SYNCH) {
			if(observers.containsKey(key)){
				observers.remove(key);
				System.out.println("--- 观察者[key="+key+" ] 已经被取消注册 -------- ----------");
			}else{
				System.out.println("--- 观察者[key="+key+" ] 不存在 ------------------------");
			}
		}
	}
	
	
	/**
	 * 通知观察者们
	 * @param key
	 * @param observer
	 */
	public void notifyObservers(String message){
		
		synchronized (SYNCH){ 
			if(observers!=null){
				for(Map.Entry<String,Observer> observerObj:observers.entrySet()){
					String key = observerObj.getKey();
					Observer observer = observerObj.getValue();
					System.out.println("--- 通知观察者[key="+key+" ] --------------------------");
					observer.notice(message);
				}				
			}
		}
	}
	
	public int countObservers(){
	        synchronized(SYNCH){
	        	return observers.size();
	        }
	}
}
