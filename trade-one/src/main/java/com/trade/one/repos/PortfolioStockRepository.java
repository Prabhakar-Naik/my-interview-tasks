package com.trade.one.repos;

import com.trade.one.models.PortfolioStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author prabhakar, @Date 26-02-2025
 */

@Repository
public interface PortfolioStockRepository extends JpaRepository<PortfolioStock, Long> {
    
    List<PortfolioStock> findByPortfolio_UserAccount_Id(Long userId);


    Optional<PortfolioStock> findByPortfolio_UserAccount_IdAndStock_Id(Long userId, Long id);
}
