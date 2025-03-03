package com.trade.one.services;

import com.trade.one.dtos.UserDTO;
import com.trade.one.dtos.UserLoginDTO;
import com.trade.one.dtos.UserSignupDTO;
import com.trade.one.models.ResetToken;
import com.trade.one.models.UserAccount;
import com.trade.one.repos.KycRepository;
import com.trade.one.repos.ResetTokenRepository;
import com.trade.one.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author prabhakar, @Date 25-02-2025
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KycRepository kycRepository;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    public String registerUser(UserSignupDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return "Email already exists";
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            return "Phone number already exists";
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setFullName(dto.getFullName());
        userAccount.setEmail(dto.getEmail());
        userAccount.setPassword(passwordEncoder.encode(dto.getPassword())); // Ideally, encrypt the password
        userAccount.setPhoneNumber(dto.getPhoneNumber());
        userAccount.setRole("USER");
        userAccount.setEmailVerified(false);
        userAccount.setPhoneVerified(false);
        userAccount.setKycCompleted(false);
        userAccount.setAccountActivated(false);

        userRepository.save(userAccount);
        return "User registered successfully. Please verify email and phone.";
    }
    
    public String loginUser(UserLoginDTO dto) {
        Optional<UserAccount> user = userRepository.findByEmail(dto.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(dto.getPassword())) { // Encrypt and compare
            return "Login successful";
        }
        return "Invalid credentials";
    }

    public String forgotPassword(String email) {
        Optional<UserAccount> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            String token = UUID.randomUUID().toString();
            ResetToken resetToken = new ResetToken(token, userOpt.get());
            resetTokenRepository.save(resetToken);
            return "Password reset token generated: " + token;
        }
        return "Email not found";
    }

    public String resetPassword(String token, String newPassword) {
        Optional<ResetToken> resetTokenOpt = resetTokenRepository.findByToken(token);
        if (resetTokenOpt.isPresent()) {
            UserAccount userAccount = resetTokenOpt.get().getUser();
            userAccount.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userAccount);
            resetTokenRepository.delete(resetTokenOpt.get());
            return "Password reset successfully";
        }
        return "Invalid or expired token";
    }

    public boolean isPresentEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO getUserById(Long userId) {
        Optional<UserAccount> user = userRepository.findById(userId);
        return user.map(this::convertToDTO).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public String updateUser(Long userId, UserDTO userDto) {
        Optional<UserAccount> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            UserAccount userAccount = userOpt.get();
            userAccount.setFullName(userDto.getFullName());
            userAccount.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(userAccount);
            return "User updated successfully.";
        }
        return "User not found.";
    }

    @Transactional
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        kycRepository.deleteByUserAccountId(userId);
        return "User deleted successfully.";
    }

    private UserDTO convertToDTO(UserAccount userAccount) {
        return new UserDTO(userAccount.getId(), userAccount.getFullName(), userAccount.getEmail(), userAccount.getPhoneNumber());
    }
    

}
