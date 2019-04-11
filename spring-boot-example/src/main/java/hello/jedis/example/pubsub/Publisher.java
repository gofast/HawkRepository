package hello.jedis.example.pubsub;

import java.util.List;
import java.util.Map;

import hello.jedis.example.JedisExample;
import redis.clients.jedis.Jedis;

public class Publisher {
	 
	public static void main(String[] args) {
		simplePubExec();
	}

	/**
	 * 实现简单的主题的发布定于模式
	 * 问题：Redis 发布/订阅机制原理 ?
	 */
	private static void simplePubExec(){
		Jedis jedis = null;
		try{
			jedis = JedisExample.getJedis(); 
			Long result = jedis.publish("channl1", "hello world !2");
			System.out.println("publish-reuslt:"+result);
			Map<String,String> map = jedis.pubsubNumSub("");
			for(Map.Entry<String, String> m:map.entrySet()){
				System.out.println("map:<"+m.getKey()+","+m.getValue()+">");
			}
			Long numPat = jedis.pubsubNumPat();
			System.out.println("numPat:"+numPat);
			List<String> titles = jedis.pubsubChannels("*");
			System.out.println("publish-title-size:"+titles.size());
			for (String title:titles) {
				System.out.println("publish-title-list:"+title);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JedisExample.closeJedis(jedis);
		}
		
	}
}
