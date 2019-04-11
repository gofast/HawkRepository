package hello.jedis.example;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接和操作Redis集群
 * @author maji48
 *
 */
public class JedisClusterExample {
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec(){
		String host="localhost";
		int port = 6379;
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort(host, 6379));
		JedisCluster jc = new JedisCluster(jedisClusterNode, 2000, 2000,2000, "password", getJedisPoolConfig());
		jc.set("foo", "bar");
		
			
	}
	private static JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}
}
