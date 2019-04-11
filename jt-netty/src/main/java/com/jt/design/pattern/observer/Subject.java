package com.jt.design.pattern.observer;
 
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; 

/**
 * ���۲��ߣ��������ɹ۲����ǹ۲��Ŀ��
 * @author maji48
 *
 */
public abstract class Subject {

	private static final ConcurrentHashMap<String,Observer> observers = new ConcurrentHashMap<String,Observer>();
	private static final Object SYNCH = new Object();
	
	/**
	 * ע��۲���
	 * @param key
	 * @param observer
	 */
	public void registerObserver(String key,Observer observer){
		
		synchronized (SYNCH) {
			if(observers.containsKey(key)){
				System.out.println("--- �۲���[key="+key+" ] �Ѿ���ע�ᣬ�¹۲���ע��ʧ�� ----------");
			}else{
				observers.put(key, observer);
				System.out.println("--- �۲���[key="+key+" ] ע��ɹ� -----------------------");
			}
		}
	}
	
	/**
	 * ȡ��ָ���۲��ߵ�ע��
	 * @param key
	 * @param observer
	 */
	public void unregisterObserver(String key){
		
		synchronized (SYNCH) {
			if(observers.containsKey(key)){
				observers.remove(key);
				System.out.println("--- �۲���[key="+key+" ] �Ѿ���ȡ��ע�� -------- ----------");
			}else{
				System.out.println("--- �۲���[key="+key+" ] ������ ------------------------");
			}
		}
	}
	
	
	/**
	 * ֪ͨ�۲�����
	 * @param key
	 * @param observer
	 */
	public void notifyObservers(String message){
		
		synchronized (SYNCH){ 
			if(observers!=null){
				for(Map.Entry<String,Observer> observerObj:observers.entrySet()){
					String key = observerObj.getKey();
					Observer observer = observerObj.getValue();
					System.out.println("--- ֪ͨ�۲���[key="+key+" ] --------------------------");
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
