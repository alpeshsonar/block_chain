package com.boot.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.boot.mvc"})
@SpringBootApplication
public class BlockChain {
    public static void main(String[] args) {
        SpringApplication.run(BlockChain.class, args);
    }
}