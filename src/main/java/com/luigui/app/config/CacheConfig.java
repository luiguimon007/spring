package com.luigui.app.config;

import java.util.HashMap;
import java.util.Map;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    /*
    @Bean
    public CacheManager getManager(RedissonClient redisson) {
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("testMap",new CacheConfig());
        return  new RedissonSpringCacheManager(redisson); //administrador de caches
    }
    @Bean(destroyMethod = "shutdown ")
    public RedissonClient getClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }*/
}
