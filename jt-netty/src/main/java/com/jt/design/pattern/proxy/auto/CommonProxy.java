package com.jt.design.pattern.proxy.auto;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CommonProxy implements InvocationHandler{
	
	private Object object;
	
	public CommonProxy(Object object){
		this.object= object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub 
		Object result = method.invoke(object, args);
		System.out.println(method.getDeclaringClass());
		if(proxy instanceof CommonProxy){
			System.out.println(proxy.getClass()); 
		}else if(proxy instanceof Proxy){ 
			System.out.println("Proxy");  
		}
		return result;
	}
	private static Class[] myClassList = new Class[]{BuyCar.class};
	
	public static void main(String[] args) {
		BuyCar car = new BuyYellowCar();
		BuyCar proxyCar = (BuyCar) Proxy.newProxyInstance(CommonProxy.class.getClassLoader(), CommonProxy.myClassList, new CommonProxy(car));
		System.out.println("result:"+proxyCar.getLoveCar());
	}
}
