package hello.jedis.example.pubsub;

import java.util.List;

import hello.jedis.example.JedisExample;
import redis.clients.jedis.Jedis;

public class Subcriber {
	
	public static void main(String[] args) {
		simpleSubExec();
	}

	/**
	 * 实现简单的主题的发布定于模式
	 */
	private static void simpleSubExec(){
		Jedis jedis = null;
		List<String> values = null;
		try{
			jedis = JedisExample.getJedis();
//			jedis.subscribe(new MyPubSub(), "channl1");
			jedis.psubscribe(new MyPubSub(), "*");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{ 
			JedisExample.closeJedis(jedis);
		}
		
	}
	
	class myThread implements Runnable{

		Jedis jedis;
		public myThread(Jedis jedis){
			this.jedis=jedis;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}
		
	}
}
