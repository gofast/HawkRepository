package hello.jedis.example;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Jedis实现操作redis
 * 通过方法rpush，lpop,lpush，rpop实现简单的消息队列
 * 原理：Redis的列表是双向列表
 * @author maji48
 *
 */
public class JedisMessageQueue {

	public static void main(String[] args) {
		simpleExec();
	}
	
	/**
	 * 实现简单队列,redis的数据结构：双向链表
	 * 向redis的列表写入元素    rpush表示从列表的右边写入元素，如果key没有，则会先创建在push，若key已经存在但不是列表类型，则报错；而rpushx/lpushx在队列为[]或者不存在时，将什么也不做；
	 * jedis.rpush(key, string...);   jedis.lpush("123", new String[]{"12","234"}); 
	 * jedis.lpush(key, string...);
	 * jedis.rpushx(key, string...);   
	 * jedis.lpushx(key, string...);  
	 * eg:jedis.lpush("123", new String[]{"12","234"}); 相当于：先执行lpush("123","12") 再执行lpush("123","234")
	 * 注：列表操作中：rpop从列表的右侧弹出元素，lpop从列表的左侧弹出元素： 而brpop和blpop则分别是rpop和lpop的阻塞版本；
	 * 
	 * jedis.rpop(key);  // 从key的列表的右侧移除并返回列表最右侧的一个元素。没有时，返回null   rpop   right pop
	 * jedis.lpop(key);  // 移除并返回列表 key 的头元素。没有时，返回null   lpop   left pop
	 * jedis.brpop(..., key); // 移除并返回列表 key 的尾元素。 是 RPOP的阻塞版本        brpop  broken right pop;
	 * jedis.blpop(..., key); // 移除并返回列表 key 的头元素。是 lpop的阻塞版本
	 * 
	 * jedis.rpoplpush(sourceKey, destinctionKey); 从sourceKey列表的右侧弹出一个元素value，并写入备份列表destinctionKey（若不存在，就创建），最后返回弹出的元素value
	 * jedis.brpoplpush(sourceKey, destinctionKey,timeout)  是rpoplpush的阻塞版本
	 * jedis.lrange(key,start,stop); // 查看列表指定范围的数据
	 * 
	 * 总结：
	 * 实现简单队列方案：
	 * 方案1：根据FIFO原则，生产命令若为rpush，对应的消费命令就是：lpop/blpop;   如果生产命令若为lpush 则对应的消费命令就是：rpop/brpop
	 * 方案2：根据FIFO原则，为了确保消息消费的可靠性可以通过备份策略来实现（目的是解决因为消费失败而丢失数据的问题）；（1、每个客户端各自私有一个备份列表；2、所有客户端公用一个备份列表）
	 * 第一步：通过rpoplpush/brpoplpush,列表右弹出一个元素value，并将value写入备份列表的左侧，返回弹出的元素value；
	 * 第二步：应用消费value
	 * 第三步：如果消费失败，再将该value放回原队列，或做其他处理，具体根据实际情况应对
	 * 第四步：如果消费成功，则通过命令lrem删除备份列表的符合条件的元素；
	 * 方案2扩展：如果原列表和备份列表是同一个列表时，此时将形成旋转队列，本轮消费失败，就会进入下一轮进行消费；
	 * 参考：
	 * http://redisdoc.com/list/lrem.html
	 * https://www.jianshu.com/p/5d98222e36c0
	 * 问题：Redis的LRU策略？ Redis还有哪些策略？
	 */
	private static void simpleExec(){
		Jedis jedis = null;
		String value = null; 
		List<String> values = null;
		try{
			jedis = JedisExample.getJedis();
			jedis.lpush("123", new String[]{"12","234"});
			value = jedis.brpoplpush("123", "456", 1000);
//			jedis.rpoplpush("123", "456");
//			jedis.rpush("123", "aa");
//			jedis.rpush("123", "aaaa");
			jedis.rpush("123", "aaaaa");
			value = jedis.rpop("123");
			System.out.println("result-pop:"+value);
			/*values = jedis.lrange("123",0,-1);
			for(String va:values){
				System.out.println("list:"+va);	
			}*/
//			 jedis.lpop(key);
//			 jedis.blpop(..., key);
//			 jedis.brpop(..., key);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			jedis.lrem("456", 1, value);
			JedisExample.closeJedis(jedis);
		}
		
	}
	
	
	
	
}
