package hello.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
@Component
public class RedisTool {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	
	// =============================common============================
	/**
	 * 设置缓存失效时间
	 * @param key
	 * @param timeout
	 * @return
	 */
	public boolean expire(String key,long timeout){
		try{
			if(timeout>0){
				return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
				
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取特定的缓存的失效时间
	 * @param key
	 * @return
	 */
	public long getExpire(String key){
		try{
			return redisTemplate.getExpire(key, TimeUnit.SECONDS);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * key是否存在
	 * @param key
	 * @return
	 */
	public boolean haskey(String key){
		try{
			return redisTemplate.hasKey(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除缓存
	 * @param keys
	 */
	public void del(String... keys){
		if(keys!=null && keys.length>0){
			if(keys.length == 1){
				redisTemplate.delete(keys[0]);
			}else{
				redisTemplate.delete(CollectionUtils.arrayToList(keys));
			}
		}
	}
	
	 // ============================String=============================
	/**
	 * 普通获取缓存值value
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return key == null ? null:this.redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 普通设置缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key,Object value){
		try{
			if(key == null){
				return false;
			}
			redisTemplate.opsForValue().set(key, value);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 普通设置缓存,并设置失效时间
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key,Object value, long timeout){
		try{
			if(key == null){
				return false;
			}
			redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 递增
	 * @param key
	 * @param delta
	 * @return
	 */
	public long incr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递增因子必须大于0");
		}
		return this.redisTemplate.opsForValue().increment(key, delta);
	}
	
	/**
	 * 递增
	 * @param key
	 * @param delta
	 * @return
	 */
	public long decr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递减因子必须大于0");
		}
		return this.redisTemplate.opsForValue().increment(key, -delta);
	}
	
	 
	
}
