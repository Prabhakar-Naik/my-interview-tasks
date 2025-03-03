package com.trade.one.services;

import com.trade.one.dtos.OrderDTO;
import com.trade.one.models.*;
import com.trade.one.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockPriceService stockPriceService;  // Injecting Stock Price Service

    @Autowired
    private OrderMatchingService orderMatchingService;

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;


    public String placeOrder(OrderDTO orderDTO) {
        //Double livePrice = stockPriceService.getLiveStockPrice(orderDTO.getStockId().toString());
        
//        if (livePrice == null) {
//            return "Failed to fetch live stock price. Try again.";
//        }

        Order order = new Order();
        Stock stock = this.stockRepository.findById(orderDTO.getStockId()).orElse(null);
        if (stock == null) {
            return "Failed to find stock.";
        }
        order.setStock(stock);
        order.setQuantity(orderDTO.getQuantity());
        order.setOrderType(orderDTO.getOrderType());
        //order.setPrice(livePrice); // Set live price
        order.setPrice(orderDTO.getPrice());
        order.setStatus("PENDING");

        orderRepository.save(order);
        // Trigger matching for the stock
        orderMatchingService.matchOrders(orderDTO.getStockId());
        return "Order placed successfully at price: " + order.getPrice();
    }

    @Transactional
    public String placeOrder(OrderDTO orderDTO, Long userId) {
        Stock stock = stockRepository.findById(orderDTO.getStockId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Portfolio portfolio = portfolioRepository.findByUserAccountId(userId)
                .orElseGet(() -> {
                    Portfolio newPortfolio = new Portfolio(new UserAccount(userId));
                    return portfolioRepository.save(newPortfolio);
                });

        PortfolioStock portfolioStock = portfolioStockRepository.findByPortfolio_UserAccount_IdAndStock_Id(userId, stock.getId())
                .orElse(null);

        if ("BUY".equalsIgnoreCase(orderDTO.getOrderType())) {
            portfolioStock = handleBuyOrder(portfolioStock, portfolio, stock, orderDTO);
        } else if ("SELL".equalsIgnoreCase(orderDTO.getOrderType())) {
            handleSellOrder(portfolioStock, orderDTO);
        } else {
            throw new RuntimeException("Invalid order type");
        }

        Stock stockOpt = this.stockRepository.findById(orderDTO.getStockId()).orElse(null);
        if (stockOpt == null) {
            return "Failed to find stock.";
        }

        Optional<UserAccount> userAccount = this.userRepository.findById(userId);
        if (userAccount.isEmpty()) {
            return "Failed to find user account. Try again.";
        }

        Order order = new Order();
        order.setUserAccount(userAccount.get());
        order.setStock(stockOpt);
        order.setQuantity(orderDTO.getQuantity());
        order.setPrice(stock.getCurrentPrice());
        order.setOrderType(orderDTO.getOrderType());
        order.setStatus("COMPLETED");

        orderRepository.save(order);
        return "Order placed successfully";
    }

    private PortfolioStock handleBuyOrder(PortfolioStock portfolioStock, Portfolio portfolio, Stock stock, OrderDTO orderDTO) {
        if (portfolioStock == null) {
            portfolioStock = new PortfolioStock(portfolio, stock, orderDTO.getQuantity(), stock.getCurrentPrice());
        } else {
            int newQuantity = portfolioStock.getQuantity() + orderDTO.getQuantity();
            double newAvgPrice = ((portfolioStock.getAveragePrice() * portfolioStock.getQuantity()) +
                    (stock.getCurrentPrice() * orderDTO.getQuantity())) / newQuantity;
            portfolioStock.setQuantity(newQuantity);
            portfolioStock.setAveragePrice(newAvgPrice);
        }
        return portfolioStockRepository.save(portfolioStock);
    }

    private void handleSellOrder(PortfolioStock portfolioStock, OrderDTO orderDTO) {
        if (portfolioStock == null || portfolioStock.getQuantity() < orderDTO.getQuantity()) {
            throw new RuntimeException("Insufficient stocks to sell");
        }
        portfolioStock.setQuantity(portfolioStock.getQuantity() - orderDTO.getQuantity());

        if (portfolioStock.getQuantity() == 0) {
            portfolioStockRepository.delete(portfolioStock);
        } else {
            portfolioStockRepository.save(portfolioStock);
        }
    }


}
