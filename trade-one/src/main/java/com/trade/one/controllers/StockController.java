package com.trade.one.controllers;

import com.trade.one.dtos.StockDTO;
import com.trade.one.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@RestController
@RequestMapping("/api/stocks")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @PostMapping(value = "/add")
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.addStock(stockDTO));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }
}