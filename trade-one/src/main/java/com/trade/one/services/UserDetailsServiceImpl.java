package com.trade.one.services;

import com.trade.one.models.UserAccount;
import com.trade.one.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<UserAccount> user = userRepository.findByEmail(username);
        //.orElseThrow(() -> new UsernameNotFoundException("User Not Found "+ username));
        if (user != null && user.isPresent()) {
            log.info("User found with username: {}", username);
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getEmail())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole())
                    .build();
        }
        log.info("User not found with username: {}", username);
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
