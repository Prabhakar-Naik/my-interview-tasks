package com.trade.one.dtos;
/**
 * @author prabhakar, @Date 26-02-2025
 */
public class OrderNotification {
    private Long orderId;
    private String status;
    private String message;

    public OrderNotification() {
        super();
    }

    public OrderNotification(Long orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
