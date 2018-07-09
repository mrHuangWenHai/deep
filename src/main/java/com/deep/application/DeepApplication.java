package com.deep.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Properties;


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
    //Jedis jedis = new Jedis("localhost");
    //System.out.println("connecting");
    //System.out.println("running"+jedis.ping());
    SpringApplication application1 = new SpringApplication(DeepApplication.class);
    application1.run(args);
    System.out.println("The project is starting up now");
  }
}
