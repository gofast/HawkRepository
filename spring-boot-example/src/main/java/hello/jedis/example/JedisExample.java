package hello.jedis.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * 单链接直接连接和操作Redis服务：适用于单节点环境，并且链接数量少的长连接情况
 * @author maji48
 *
 */
public class JedisExample {
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec(){
		String ip="localhost";
		int port = 6379;
		String password="123456";
		Jedis jedis = new Jedis("10.107.20.33", 6379);
		jedis.auth("@dev20180315a");
		jedis.connect();
		if(jedis.isConnected()){
			jedis.select(9);
			String value = jedis.get("123");
			System.out.println("查询redis [ 123="+value+" ]");
		}else{
			System.out.println("连接redis失败");
		}
	}
	public static Jedis getJedis(){
		Jedis jedis = new Jedis("10.107.20.33", 6379);
		jedis.auth("@dev20180315a");
		jedis.connect();
		if(jedis.isConnected()){
//			jedis.select(9);
			return jedis;
		}
		return null;
	}
	
	public static void closeJedis(Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
	private static void otherOperate(Jedis jedis){
		jedis.disconnect();//断开连接
		Set<String> keys = jedis.keys("*"); //列出所有的key
		Set<String> keys1 = jedis.keys("key"); //查找特定的key
		//移除给定的一个或多个key,如果key不存在,则忽略该命令. 
		jedis.del("key1");
		jedis.del("key1","key2","key3","key4","key5");
		//移除给定key的生存时间(设置这个key永不过期)
		jedis.persist("key1"); 
		//检查给定key是否存在
		jedis.exists("key1"); 
		//将key改名为newkey,当key和newkey相同或者key不存在时,返回一个错误
		jedis.rename("key1", "key2");
		//返回key所储存的值的类型。 
		//none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表) 
		jedis.type("key1");
		//设置key生存时间，当key过期时，它会被自动删除。 
		jedis.expire("key1", 5);//5秒过期 
		//字符串值value关联到key。 
		jedis.set("key1", "value1"); 
		//将值value关联到key，并将key的生存时间设为seconds(秒)。 
		jedis.setex("foo", 5, "haha"); 
		//清空所有的key
		jedis.flushAll();
		//返回key的个数 
		jedis.dbSize();
		//哈希表key中的域field的值设为value。 
		jedis.hset("key1", "field1", "field1-value"); 
		jedis.hset("key1", "field2", "field2-value"); 
		Map map = new HashMap(); 
		map.put("field1", "field1-value"); 
		map.put("field2", "field2-value"); 
		jedis.hmset("key1", map); 
		//返回哈希表key中给定域field的值 
		jedis.hget("key1", "field1");
		//返回哈希表key中给定域field的值(多个)
		List list = jedis.hmget("key1","field1","field2"); 
		for(int i=0;i<list.size();i++){ 
		   System.out.println(list.get(i)); 
		} 

		//返回哈希表key中所有域和值
		Map<String,String> map1 = jedis.hgetAll("key1"); 
		for(Map.Entry entry: map1.entrySet()) { 
		   System.out.print(entry.getKey() + ":" + entry.getValue() + "\t"); 
		} 
		//删除哈希表key中的一个或多个指定域
		jedis.hdel("key1", "field1");
		jedis.hdel("key1", "field1","field2");
		//查看哈希表key中，给定域field是否存在。 
		jedis.hexists("key1", "field1");
		//返回哈希表key中的所有域
		jedis.hkeys("key1");
		//返回哈希表key中的所有值
		jedis.hvals("key1");
		//将值value插入到列表key的表头。 
		jedis.lpush("key1", "value1-0"); 
		jedis.lpush("key1", "value1-1"); 
		jedis.lpush("key1", "value1-2"); 
		//返回列表key中指定区间内的元素,区间以偏移量start和stop指定.
		//下标(index)参数start和stop从0开始;
		//负数下标代表从后开始(-1表示列表的最后一个元素,-2表示列表的倒数第二个元素,以此类推)
		List list1 = jedis.lrange("key1", 0, -1);//stop下标也在取值范围内(闭区间)
		for(int i=0;i<list1.size();i++){ 
		   System.out.println(list1.get(i)); 
		} 
		//返回列表key的长度。 
		jedis.llen("key1");
		//将member元素加入到集合key当中。 
		jedis.sadd("key1", "value0"); 
		jedis.sadd("key1", "value1"); 
		//移除集合中的member元素。 
		jedis.srem("key1", "value1"); 
		//返回集合key中的所有成员。 
		Set set = jedis.smembers("key1"); 
		//判断元素是否是集合key的成员
		jedis.sismember("key1", "value2");
		//返回集合key的元素的数量
		jedis.scard("key1");
		//返回一个集合的全部成员，该集合是所有给定集合的交集
		jedis.sinter("key1","key2");
		//返回一个集合的全部成员，该集合是所有给定集合的并集
		jedis.sunion("key1","key2");
		//返回一个集合的全部成员，该集合是所有给定集合的差集
		jedis.sdiff("key1","key2");
	}
}
