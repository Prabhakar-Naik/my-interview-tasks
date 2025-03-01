package com.trade.one.repos;

import com.trade.one.models.KycDetails;
import com.trade.one.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Repository
public interface KycRepository extends JpaRepository<KycDetails, Long> {
    boolean existsByPanCardNumber(String panCardNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);

    Optional<KycDetails> findByUserAccountId(Long userAccountId);

    void deleteByUserAccountId(Long userAccountId);

    Optional<KycDetails> findByUserAccount(UserAccount user);
}
