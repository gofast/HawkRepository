package hello.configuration;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
/*@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })*/
public class RedisConfig {

	 /* @Bean
      @ConditionalOnMissingBean(name = "redisTemplate")
      public RedisTemplate<Object, Object> redisTemplate(
              RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
          RedisTemplate<Object, Object> template = new RedisTemplate<>();
          template.setConnectionFactory(redisConnectionFactory);
          return template;
      }
  
      @Bean
      @ConditionalOnMissingBean
      public StringRedisTemplate stringRedisTemplate(
              RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
          StringRedisTemplate template = new StringRedisTemplate();
          template.setConnectionFactory(redisConnectionFactory);
          return template;
      }*/
	
      @Bean
      @SuppressWarnings("all")
      public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    	  RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    	  template.setConnectionFactory(factory);
    	  Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    	  ObjectMapper om = new ObjectMapper();
    	  om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
    	  om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    	  jackson2JsonRedisSerializer.setObjectMapper(om);
    	  StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    	  // key采用String的序列化方式
    	  template.setKeySerializer(stringRedisSerializer);
    	  // hash的key也采用String的序列化方式
    	  template.setHashKeySerializer(stringRedisSerializer);
    	  // value序列化方式采用jackson
    	  template.setValueSerializer(jackson2JsonRedisSerializer);
    	  // hash的value序列化方式采用jackson
    	  template.setHashValueSerializer(jackson2JsonRedisSerializer);
    	  template.afterPropertiesSet();
    	  return template;
      }
}
