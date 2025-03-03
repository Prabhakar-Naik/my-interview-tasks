package com.trade.one.services;

import com.trade.one.dtos.StockDTO;
import com.trade.one.models.Stock;
import com.trade.one.repos.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public StockDTO addStock(StockDTO stockDTO) {
        Stock stock = new Stock(stockDTO);
        stock = stockRepository.save(stock);
        return new StockDTO(stock);
    }

    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(StockDTO::new).collect(Collectors.toList());
    }


}