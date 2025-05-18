package com.example.OTP_AuthenticationWithSpringBoot.controller;

import com.example.OTP_AuthenticationWithSpringBoot.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateOTP(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(phoneNumber);
        System.out.printf("OTP: %s", otp);
        return ResponseEntity.ok("OTP sent successfully!");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestParam String phoneNumber, @RequestParam String otp) {
        String storedOtp = otpService.validateOtp(phoneNumber);

        if (storedOtp == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ OTP expired or invalid!");
        }

        if (!storedOtp.equals(otp)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Incorrect OTP!");
        }

        return ResponseEntity.ok("✅ OTP verified successfully!");
    }


}
