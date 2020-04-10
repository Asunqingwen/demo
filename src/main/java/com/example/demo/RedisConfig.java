package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("spring.redis.cluster")
public class RedisConfig {
     //通过 @ConfigurationProperties 注解声明配置文件前缀，将配置文件中定义的 ports 数组、host
     //及连接池配置信息都将被注入 port、host、poolConfig 三个属性中。

    List<Integer> ports;
    String host;
    JedisPoolConfig poolConfig;

    // 配置 RedisClusterConfiguration 实例
    @Bean
    RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        List<RedisNode> nodes = new ArrayList<>();
        for (Integer port:ports){
            nodes.add(new RedisNode(host,port));
        }
        configuration.setPassword(RedisPassword.of("123"));  //设置redis登录密码
        configuration.setClusterNodes(nodes);  //设置redis节点信息
        return configuration;
    }

    // 根据 RedisClusterConfiguration 实例以及连接池配置信息创建 Jedis 连接工厂 JedisConnectionFactory
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration(),poolConfig);
        return factory;
    }

    // 根据 JedisConnectionFactory 创建 RedisTemplate，同时配置 key 和 value 序列号方式。
    @Bean
    RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    // 根据 JedisConnectionFactory 创建 StringRedisTemplate，同时配置 key 和 value 序列号方式。
    @Bean
    StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }

    public List<Integer> getPorts(){
        return ports;
    }

    public void setPorts(List<Integer> ports){
        this.ports = ports;
    }

    public String getHost(){
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public JedisPoolConfig getPoolConfig(){
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig){
        this.poolConfig = poolConfig;
    }
}
