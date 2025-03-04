package com.trade.one.controllers;

import com.trade.one.dtos.TradeDTO;
import com.trade.one.services.PortfolioService;
import com.trade.one.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@RestController
@RequestMapping("/api/trades")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/history")
    public ResponseEntity<List<TradeDTO>> getUserTrades(@RequestParam Long userId) {
        return ResponseEntity.ok(tradeService.getUserTrades(userId));
    }

    @PostMapping(value = "/tradeOne")
    public ResponseEntity<String> createTradeAndPortfolio(@RequestBody TradeDTO tradeDTO) {
        return ResponseEntity.ok(this.portfolioService.updatePortfolioAfterTrade(tradeDTO));
    }



}
