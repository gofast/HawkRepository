package hello.jedis.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolExample implements Runnable{

	private Jedis jedis;
	private String title;
	private long time;
	
	public JedisPoolExample(Jedis jedis,String title, long time){
		this.jedis = jedis;
		this.title = title;
		this.time = time;
	}
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec(){
		String host="10.107.20.33";
		int port = 6379;
		String password="@dev20180315a"; 
		JedisPool jedisPool = new JedisPool(getJedisPoolConfig(),host, port,2000,password,9,false,null,null,null);
		try{ 
			for (int i = 0; i < 10; i++) {
				Thread th = null;
				if(i<5){
					th = new Thread(new JedisPoolExample(jedisPool.getResource(),"t"+i,300));					
				}else{
					th = new Thread(new JedisPoolExample(jedisPool.getResource(),"t"+i,100));
				}
				th.start();
			}
			/*if(jedis.isConnected()){
				jedis.select(9);
				String value = jedis.get("123");
				System.out.println("查询redis [ 123="+value+" ]");
				
			}else{
				System.out.println("连接redis失败");
			}*/
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		
	}
	
	private static JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	@Override
	public void run() {
		try{
			System.out.println("\n-- "+title+" :start ----------------------------------------------------------------\n");
			if(jedis.isConnected()){
				jedis.select(9);
				String value = jedis.get("123");
				System.out.println(title+" :查询redis [ 123="+value+" ]");
				jedis.set(title, title+":123456");
				for(int i=0;i<5;i++){
					Thread.sleep(time);
					System.out.println(title+" :查询redis [ "+title+"="+jedis.get(title)+" ]");
				}
				
			}else{
				System.out.println(title+" :连接redis失败");
			}
		}catch(Exception ex){
			
			
		}finally{
			if(jedis!=null){
				// 释放资源，放回jedis资源池
				jedis.close();
			}
			System.out.println("\n-- "+title+" end ----------------------------------------------------------------\n");
		}
	}
}
