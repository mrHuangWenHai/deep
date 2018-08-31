package com.deep.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import redis.clients.jedis.Jedis;


import static com.deep.domain.service.ServiceConfiguration.redisServer;


/**
 * Created by huangwenhai on 2018/1/31.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.deep"})
@MapperScan("com.deep.infra")
public class DeepApplication {
  public static void main(String[] args) throws Exception {

    //验证redis
    Jedis jedis = new Jedis(redisServer);
    System.out.println("connecting");
    System.out.println("running"+jedis.ping());
    SpringApplication.run(DeepApplication.class, args);
    System.out.println("The project is starting up now");
  }
}
