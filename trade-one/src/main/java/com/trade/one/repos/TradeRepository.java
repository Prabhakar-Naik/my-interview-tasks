package com.trade.one.repos;

import com.trade.one.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByBuyOrder_IdOrSellOrder_Id(Long buyOrderId, Long sellOrderId);
}
