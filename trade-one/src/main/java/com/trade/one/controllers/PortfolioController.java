package com.trade.one.controllers;

import com.trade.one.dtos.PortfolioDTO;
import com.trade.one.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public ResponseEntity<PortfolioDTO> getUserPortfolio(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getUserPortfolio(userId));
    }
}
