package com.trade.one.repos;

import com.trade.one.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserAccountId(Long id);

    // For BUY orders
    List<Order> findByStockIdAndOrderTypeOrderByPriceDescTimestampAsc(Long stockId, String buy);

    // For SELL orders
    List<Order> findByStockIdAndOrderTypeOrderByPriceAscTimestampAsc(Long stockId, String sell);
}