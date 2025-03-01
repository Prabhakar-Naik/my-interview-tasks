package com.trade.one.dtos;

import java.util.List;
/**
 * @author prabhakar, @Date 26-02-2025
 */
public class PortfolioDTO {
    private Long userId;
    private List<PortfolioStockDTO> stocks;

    public PortfolioDTO() {

    }
    public PortfolioDTO(Long userId, List<PortfolioStockDTO> stocks) {
        this.userId = userId;
        this.stocks = stocks;
    }

    public List<PortfolioStockDTO> getStocks() {
        return stocks;
    }
    public void setStocks(List<PortfolioStockDTO> stocks) {
        this.stocks = stocks;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
