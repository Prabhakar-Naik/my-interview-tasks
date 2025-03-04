package com.trade.one.controllers;

import com.trade.one.dtos.KycDetailsDTO;
import com.trade.one.services.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author prabhakar, @Date 25-02-2025
 */

@RestController
@RequestMapping(value = "/api/kyc")
public class KycController {

    @Autowired
    private KycService kycService;

    @PostMapping("/submit/{userId}")
    public ResponseEntity<String> submitKyc(@PathVariable Long userId, @RequestBody KycDetailsDTO kycDto) {
        String response = kycService.submitKyc(userId, kycDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify/{userId}")
    public ResponseEntity<String> verifyKyc(@PathVariable Long userId) {
        String response = kycService.verifyKyc(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getKycByUserId/{userId}")
    public ResponseEntity<KycDetailsDTO> getKycByUserId(@PathVariable Long userId) {
        KycDetailsDTO kycDto = kycService.getKycByUserId(userId);
        return ResponseEntity.ok(kycDto);
    }

    @DeleteMapping("/deleteKyc/{userId}")
    public ResponseEntity<String> deleteKyc(@PathVariable Long userId) {
        String response = kycService.deleteKyc(userId);
        return ResponseEntity.ok(response);
    }

}
