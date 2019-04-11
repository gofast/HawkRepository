package hello.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

import hello.configuration.RedisTool;
import hello.model.User;


@RestController      // @RestController  相当于 @Controller 加上  @ResponseBody
public class RedisController extends SpringBootServletInitializer{
  
	@Autowired
	private RedisTool redis;
	
	@RequestMapping(value="/example/expire",method = RequestMethod.GET)
	public boolean expire(String key,long timeout){
		return redis.expire(key, timeout);
	}
	
	/**
	 * 获取特定的缓存的失效时间
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/example/getExpire",method = RequestMethod.GET)
	public long getExpire(String key){
		return redis.getExpire(key);
	}
	
	/**
	 * key是否存在
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/example/haskey",method = RequestMethod.GET)
	public boolean haskey(String key){
		return redis.haskey(key);
	}

	/**
	 * 删除缓存
	 * @param keys
	 */
	@RequestMapping(value="/example/del",method = RequestMethod.GET)
	public void del(String... keys){
		redis.del(keys);
	}
	
	 // ============================String=============================
	/**
	 * 普通获取缓存值value
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/example/get",method = RequestMethod.GET)
	public Object get(String key){
		return redis.get(key);
	}
	
	/**
	 * 普通设置缓存
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/example/set",method = RequestMethod.GET)
	public boolean set(String key,String value){
		JSONPObject objectVlaue = new JSONPObject(key,new User(value,12)); 
		return redis.set(key, objectVlaue);
	}
	/**
	 * 普通设置缓存,并设置失效时间
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/example/set_timeout",method = RequestMethod.GET)
	public boolean set(String key,Object value, long timeout){
		return redis.set(key, value, timeout);
	}
	
	/**
	 * 递增
	 * @param key
	 * @param delta
	 * @return
	 */
	@RequestMapping(value="/example/incr",method = RequestMethod.GET)
	public long incr(String key, long delta){
		return redis.incr(key, delta);
	}
	
	/**
	 * 递增
	 * @param key
	 * @param delta
	 * @return
	 */
	@RequestMapping(value="/example/decr",method = RequestMethod.GET)
	public long decr(String key, long delta){
		return redis.decr(key, delta);
	}
	
}
