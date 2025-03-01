package com.trade.one.dtos;
/**
 * @author prabhakar, @Date 25-02-2025
 */
public class OrderDTO {
    private Long stockId;
    private int quantity;
    private Double price;
    private String orderType;

    public OrderDTO() {
        super();
    }
    public OrderDTO(Long stockId, int quantity, Double price, String orderType) {
        super();
        this.stockId = stockId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }
    public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}