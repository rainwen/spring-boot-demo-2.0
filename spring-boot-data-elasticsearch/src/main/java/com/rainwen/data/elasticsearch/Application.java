package com.rainwen.data.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @author rain.wen
 * @since 2018/7/23 19:17
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class);
        new CountDownLatch(1).await();
    }
}
