package com.trade.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeOneApplication.class, args);
        System.out.println("Trade One Application Started...@");
    }

}
