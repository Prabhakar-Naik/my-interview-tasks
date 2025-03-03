package com.trade.one.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author prabhakar, @Date 26-02-2025
 */
@Service
public class StockPriceWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final Random random = new Random();

    public StockPriceWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000) // Every 5 seconds
    public void sendStockPrices() {
        double price = 100 + (random.nextDouble() * 50);
        messagingTemplate.convertAndSend("/topic/stock-price", price);
    }

}
