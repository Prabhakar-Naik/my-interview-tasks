package com.trade.one.controllers;

import com.trade.one.dtos.OrderDTO;
import com.trade.one.services.OrderService;
import com.trade.one.services.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockPriceService stockPriceService;

    @PostMapping(value = "/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.placeOrder(orderDTO));
    }

    @GetMapping("/stock-price")
    public ResponseEntity<Double> getLiveStockPrice(@RequestParam String stockSymbol) {
        return ResponseEntity.ok(stockPriceService.getLiveStockPrice(stockSymbol));
    }
}
