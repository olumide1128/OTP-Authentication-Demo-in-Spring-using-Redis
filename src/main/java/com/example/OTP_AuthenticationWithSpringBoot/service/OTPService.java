package com.example.OTP_AuthenticationWithSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit OTP
        redisTemplate.opsForValue().set(phoneNumber, otp, Duration.ofSeconds(60)); // Store OTP with 60s expiry

        return otp;
    }

    public String validateOtp(String phoneNumber) {
        return redisTemplate.opsForValue().get(phoneNumber);
    }
}
