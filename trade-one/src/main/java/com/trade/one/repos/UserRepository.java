package com.trade.one.repos;

import com.trade.one.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserAccount> findByEmail(String email);
}
