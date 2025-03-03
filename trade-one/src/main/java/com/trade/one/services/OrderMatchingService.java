package com.trade.one.services;

import com.trade.one.dtos.OrderNotification;
import com.trade.one.models.Order;
import com.trade.one.models.Trade;
import com.trade.one.repos.OrderRepository;
import com.trade.one.repos.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Service
public class OrderMatchingService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private WebSocketNotificationService notificationService;

    @Transactional
    public void matchOrders(Long stockId) {
        List<Order> buyOrders = orderRepository.findByStockIdAndOrderTypeOrderByPriceDescTimestampAsc(stockId, "BUY");
        List<Order> sellOrders = orderRepository.findByStockIdAndOrderTypeOrderByPriceAscTimestampAsc(stockId, "SELL");

        for (Order buyOrder : buyOrders) {
            for (Order sellOrder : sellOrders) {
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    int tradedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                    Double tradePrice = sellOrder.getPrice(); // Use sell price for execution
                    String timestamp = java.time.Instant.now().toString();

                    Trade trade = new Trade(buyOrder, sellOrder, tradedQuantity, tradePrice, timestamp);
                    tradeRepository.save(trade);

                    buyOrder.setQuantity(buyOrder.getQuantity() - tradedQuantity);
                    sellOrder.setQuantity(sellOrder.getQuantity() - tradedQuantity);

                    if (buyOrder.getQuantity() == 0) {
                        buyOrder.setStatus("COMPLETED");
                        notificationService.sendOrderNotification(
                                buyOrder.getUserAccount().getId(),
                                new OrderNotification(buyOrder.getId(), "COMPLETED", "Your buy order has been fulfilled.")
                        );
                    } else {
                        buyOrder.setStatus("PARTIALLY_FILLED");
                        notificationService.sendOrderNotification(
                                buyOrder.getUserAccount().getId(),
                                new OrderNotification(buyOrder.getId(), "PARTIALLY_FILLED", "Your buy order was partially filled.")
                        );
                    }

                    if (sellOrder.getQuantity() == 0) {
                        sellOrder.setStatus("COMPLETED");
                        notificationService.sendOrderNotification(
                                sellOrder.getUserAccount().getId(),
                                new OrderNotification(sellOrder.getId(), "COMPLETED", "Your sell order has been fulfilled.")
                        );
                    } else {
                        sellOrder.setStatus("PARTIALLY_FILLED");
                        notificationService.sendOrderNotification(
                                sellOrder.getUserAccount().getId(),
                                new OrderNotification(sellOrder.getId(), "PARTIALLY_FILLED", "Your sell order was partially filled.")
                        );
                    }

                    orderRepository.save(buyOrder);
                    orderRepository.save(sellOrder);

                    if (buyOrder.getQuantity() == 0) break;
                }
            }
        }
    }

}
