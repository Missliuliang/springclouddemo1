package com.web.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;


/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 11:11
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        //对象的序列化
        RedisSerializationContext.SerializationPair valueSerializationPair
                = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer());
        RedisCacheConfiguration redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
        //全局redis缓存过期时间
        redisCacheConfiguration.entryTtl(Duration.ofDays(1));
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory),redisCacheConfiguration);
    }

    public ObjectMapper objectMapper(){
        ObjectMapper mapper=new ObjectMapper();
        //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper;

    }

    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer jsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        jsonRedisSerializer.setObjectMapper(objectMapper());
        return jsonRedisSerializer;
    }




}
