package com.gatewayadmin.config;

import com.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 17:20
 */
@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
