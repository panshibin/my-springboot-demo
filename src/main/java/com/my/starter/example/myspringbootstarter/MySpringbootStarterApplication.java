package com.my.starter.example.myspringbootstarter;

import com.spi.test.MyBean;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MySpringbootStarterApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MySpringbootStarterApplication.class, args);
        System.out.println(run.getBean(MyBean.class));
    }

    @Autowired
    RedissonClient redissonClient;

    @GetMapping(value = "/say")
    public String say() {
        RBucket<Object> bucket = redissonClient.getBucket("name");
        if (bucket.get() == null) {
            bucket.set("xxxx" + System.currentTimeMillis());
        }
        return bucket.get().toString();
    }

}
