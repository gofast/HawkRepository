package test;

public class Calculate {

	public static void main(String[] args) {
		/*double currentPrice = 9.78;
		int currentNum = 3100;
		double minePrice = 14.58;
		int futureNum = 3000;
		
		double mineCost = (futureNum*currentPrice+currentNum*minePrice)/(double)(currentNum+futureNum);
		System.out.println("数量："+futureNum);
		System.out.println("总额："+futureNum*currentPrice);
		System.out.println("市值："+mineCost);
		System.out.println("总数："+(currentNum+futureNum));*/
		/*myObj a=test();
		System.out.println("result:"+a.a+","+a.b+":"+a);*/
		System.out.println("result:"+test2());
	}
	
	public static myObj test(){
		myObj a = new myObj();
		try{
			return a;
		}catch(Exception e){
		   a=null;	
		}finally {
			a.a=100;
			a.b=9;
			System.out.println("result:"+a.a+","+a.b+":"+a);
		} 
		return a;
	}
	
	
	public static void teee(myObj a){
		a.a=100;
		a.b=9;
		System.out.println("result:"+a.a+","+a.b+":"+a);
	}
	
	
	public static int test2(){
		int a = 0;
		try{
			a = 10;
		}catch(Exception e){
		   a=-1;	
		}finally {
			a=100; 
			System.out.println("result:"+a);
		} 
		return a;
	}
	 
	
}

class myObj{
	Integer a;
	Integer b;
	public myObj(){
		
	}
}