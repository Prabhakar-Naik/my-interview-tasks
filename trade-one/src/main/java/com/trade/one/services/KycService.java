package com.trade.one.services;

import com.trade.one.dtos.KycDetailsDTO;
import com.trade.one.models.KycDetails;
import com.trade.one.models.UserAccount;
import com.trade.one.repos.KycRepository;
import com.trade.one.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author prabhakar, @Date 25-02-2025
 */

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepository;

    @Autowired
    private UserRepository userRepository;


    public String submitKyc(Long userId, KycDetailsDTO kycDto) {
        if (kycRepository.existsByPanCardNumber(kycDto.getPanCardNumber())) {
            return "PAN number already exists";
        }
        if (kycRepository.existsByAadhaarNumber(kycDto.getAadhaarNumber())) {
            return "Aadhaar number already exists";
        }

        Optional<UserAccount> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return "User not found";
        }

        UserAccount userAccount = userOpt.get();
        KycDetails kycDetails = new KycDetails();
        kycDetails.setUserAccount(userAccount);
        kycDetails.setPanCardNumber(kycDto.getPanCardNumber());
        kycDetails.setAadhaarNumber(kycDto.getAadhaarNumber());
        kycDetails.setBankAccountNumber(kycDto.getBankAccountNumber());
        kycDetails.setIfscCode(kycDto.getIfscCode());
        kycDetails.setVerified(false);

        kycRepository.save(kycDetails);
        return "KYC details submitted. Awaiting verification.";
    }

    public String submitKyc1(KycDetailsDTO kycDetailsDTO) {
        Optional<UserAccount> userOpt = userRepository.findById(kycDetailsDTO.getUserId());
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setKycDetails(kycDetailsDTO.toEntity(user));
            user.setKycCompleted(false);
            userRepository.save(user);
            return "KYC submitted successfully. Awaiting verification.";
        }
        return "User not found";
    }

    public String verifyKyc1(Long userId) {
        Optional<UserAccount> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setKycCompleted(true);
            userRepository.save(user);
            return "KYC verified successfully.";
        }
        return "User not found";
    }

    public String submitKyc2(KycDetailsDTO kycDetailsDTO) {
        Optional<UserAccount> userOpt = userRepository.findById(kycDetailsDTO.getUserId());
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            KycDetails kycDetails = kycDetailsDTO.toEntity(user);
            kycRepository.save(kycDetails); // Save KYC details separately
            user.setKycCompleted(false);
            userRepository.save(user);
            return "KYC submitted successfully. Awaiting verification.";
        }
        return "User not found";
    }

    public String verifyKyc2(Long userId) {
        Optional<UserAccount> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            Optional<KycDetails> kycDetailsOpt = kycRepository.findByUserAccount(user);
            if (kycDetailsOpt.isPresent()) {
                KycDetails kycDetails = kycDetailsOpt.get();
                kycDetails.setVerified(true);
                kycRepository.save(kycDetails);
                user.setKycCompleted(true);
                userRepository.save(user);
                return "KYC verified successfully.";
            }
            return "KYC details not found.";
        }
        return "User not found.";
    }

    public String verifyKyc(Long userAccountId) {
        Optional<KycDetails> kycOpt = kycRepository.findByUserAccountId(userAccountId);
        if (kycOpt.isEmpty()) {
            return "KYC details not found";
        }

        KycDetails kyc = kycOpt.get();
        kyc.setVerified(true);
        kycRepository.save(kyc);

        UserAccount userAccount = kyc.getUserAccount();
        userAccount.setKycCompleted(true);
        userAccount.setAccountActivated(true);
        userRepository.save(userAccount);

        return "KYC verified and account activated.";
    }

    public KycDetailsDTO getKycByUserId(Long userAccountId) {
        Optional<KycDetails> kycOpt = kycRepository.findByUserAccountId(userAccountId);
        return kycOpt.map(this::convertToDTO).orElse(null);
    }

    @Transactional
    public String deleteKyc(Long userAccountId) {
        kycRepository.deleteByUserAccountId(userAccountId);
        userRepository.deleteById(userAccountId);
        return "KYC details deleted successfully.";
    }

    private KycDetailsDTO convertToDTO(KycDetails kyc) {
        return new KycDetailsDTO(kyc.getUserAccount().getId(),kyc.getAadhaarNumber(), kyc.getPanCardNumber(), kyc.getBankAccountNumber(), kyc.getIfscCode());
    }


}
