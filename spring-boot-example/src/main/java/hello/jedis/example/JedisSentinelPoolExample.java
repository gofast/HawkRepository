package hello.jedis.example;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelPoolExample {

	
	public static void main(String[] args) {
		exec();
	}

	public static void exec(){
		String masterName="mymaster";
        Set<String>set=new HashSet<String>();
        set.add("192.168.138.128:26380");
        set.add("192.168.138.128:26381");
        set.add("192.168.138.128:26382"); 
        // 哨兵模式
        JedisSentinelPool jSentinelPool=new JedisSentinelPool(masterName,set,new JedisPoolConfig());
        Jedis jedis = null;
        try{
        	jedis = jSentinelPool.getResource();
        	
        }catch(Exception ex){
        	
        }finally{
        	if(jedis!=null){
        		jedis.close();
        	}
        	if(jSentinelPool!=null){
        		jSentinelPool.close();
        	}
        }
	}
	
}
