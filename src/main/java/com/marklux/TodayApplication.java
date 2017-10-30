package com.marklux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Created by mark on 17/10/23.
 */
@EnableAutoConfiguration
@SpringBootConfiguration
public class TodayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodayApplication.class,args);
    }
}
