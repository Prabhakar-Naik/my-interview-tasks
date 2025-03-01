package com.trade.one.repos;

import com.trade.one.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
