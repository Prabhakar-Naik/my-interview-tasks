package com.trade.one.services;

import com.trade.one.dtos.OrderNotification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Service
public class WebSocketNotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendOrderNotification(Long userId, OrderNotification notification) {
        messagingTemplate.convertAndSend("/topic/orders/" + userId, notification);
    }

}
