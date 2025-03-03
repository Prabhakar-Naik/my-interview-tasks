package com.trade.one.services;

import com.trade.one.dtos.TradeDTO;
import com.trade.one.models.Trade;
import com.trade.one.repos.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<TradeDTO> getUserTrades(Long userId) {
        List<Trade> trades = tradeRepository.findByBuyOrder_IdOrSellOrder_Id(userId, userId);
        return trades.stream().map(trade -> new TradeDTO(
            trade.getBuyOrder().getId(),
            trade.getSellOrder().getId(),
            trade.getQuantity(),
            trade.getPrice(),
            trade.getTimestamp()
        )).collect(Collectors.toList());
    }

}
