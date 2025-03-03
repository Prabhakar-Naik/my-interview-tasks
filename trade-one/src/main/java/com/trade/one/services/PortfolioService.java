package com.trade.one.services;

import com.trade.one.dtos.PortfolioDTO;
import com.trade.one.dtos.PortfolioStockDTO;
import com.trade.one.dtos.StockDTO;
import com.trade.one.dtos.TradeDTO;
import com.trade.one.models.*;
import com.trade.one.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PortfolioStockRepository portfolioStockRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String updatePortfolioAfterTrade(TradeDTO tradeDTO) {
        Trade trade = new Trade();
        Optional<Order> buyOrder = this.orderRepository.findById(tradeDTO.getBuyOrderId());
        Optional<Order> sellOrder = this.orderRepository.findById(tradeDTO.getSellOrderId());
        if (buyOrder.isEmpty()){
            return "Invalid order ID";
        }
        if (sellOrder.isEmpty()){
            return "Invalid order ID";
        }
        trade.setBuyOrder(buyOrder.get());
        trade.setSellOrder(sellOrder.get());
        trade.setQuantity(tradeDTO.getQuantity());
        trade.setPrice(tradeDTO.getPrice());
        trade.setTimestamp(LocalDate.now().toString());

        updateBuyerPortfolio(trade);
        updateSellerPortfolio(trade);
        return "Success";
    }

    private void updateBuyerPortfolio(Trade trade) {
        Portfolio buyerPortfolio = portfolioRepository.findByUserAccountId(trade.getBuyOrder().getId())
                .orElseGet(() -> createPortfolio(trade.getBuyOrder().getId()));

        PortfolioStock stockHolding = buyerPortfolio.getStocks().stream()
                .filter(ps -> ps.getStock().getId().equals(trade.getBuyOrder().getStock().getId()))
                .findFirst()
                .orElseGet(() -> {
                    PortfolioStock newStock = new PortfolioStock();
                    newStock.setPortfolio(buyerPortfolio);
                    newStock.setStock(trade.getBuyOrder().getStock());
                    newStock.setQuantity(0);
                    newStock.setAveragePrice(0.0);
                    return newStock;
                });

        // Update stock quantity and average price
        int newTotalQuantity = stockHolding.getQuantity() + trade.getQuantity();
        double newAveragePrice = ((stockHolding.getQuantity() * stockHolding.getAveragePrice()) 
                                    + (trade.getQuantity() * trade.getPrice())) / newTotalQuantity;

        stockHolding.setQuantity(newTotalQuantity);
        stockHolding.setAveragePrice(newAveragePrice);
        portfolioStockRepository.save(stockHolding);
        tradeRepository.save(trade);
    }

    private void updateSellerPortfolio(Trade trade) {
        Portfolio sellerPortfolio = portfolioRepository.findByUserAccountId(trade.getSellOrder().getId())
                .orElseGet(() -> createPortfolio(trade.getSellOrder().getId()));

        PortfolioStock stockHolding = sellerPortfolio.getStocks().stream()
                .filter(ps -> ps.getStock().getId().equals(trade.getSellOrder().getStock().getId()))
                .findFirst()
                .orElse(null);

        if (stockHolding != null) {
            stockHolding.setQuantity(stockHolding.getQuantity() - trade.getQuantity());

            if (stockHolding.getQuantity() == 0) {
                sellerPortfolio.getStocks().remove(stockHolding);
                portfolioStockRepository.delete(stockHolding);
                tradeRepository.delete(trade);
            } else {
                portfolioStockRepository.save(stockHolding);
                tradeRepository.save(trade);
            }
        }
    }

    private Portfolio createPortfolio(Long userId) {
        Portfolio portfolio = new Portfolio();
        UserAccount user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        portfolio.setUserAccount(new UserAccount(userId));
        return portfolioRepository.save(portfolio);
    }


    public PortfolioDTO getUserPortfolio(Long userId) {
        Portfolio portfolio = portfolioRepository.findByUserAccountId(userId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        List<PortfolioStockDTO> stockDTOs = portfolioStockRepository.findByPortfolio_UserAccount_Id(userId)
                .stream()
                .map(stock -> new PortfolioStockDTO(
                        stock.getStock().getTickerSymbol(),
                        stock.getQuantity(),
                        stock.getAveragePrice()))
                .collect(Collectors.toList());

        PortfolioDTO dto = new PortfolioDTO();
        dto.setUserId(portfolio.getUserAccount().getId());
        dto.setStocks(stockDTOs);
        return dto;
    }



}
