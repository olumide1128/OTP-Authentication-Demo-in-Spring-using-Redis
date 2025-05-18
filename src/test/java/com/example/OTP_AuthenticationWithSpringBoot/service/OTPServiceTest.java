package com.example.OTP_AuthenticationWithSpringBoot.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

class OTPServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private OTPService otpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testGenerateOtp() {
        String phoneNumber = "+123456789";
        String otp = otpService.generateOtp(phoneNumber);

        assertNotNull(otp); // OTP should not be null
        assertEquals(6, otp.length()); // OTP should be 6 digits

        verify(valueOperations).set(eq(phoneNumber), eq(otp), eq(Duration.ofSeconds(60))); // Verify Redis store
    }

    @Test
    void testValidateOtp() {
        String phoneNumber = "+123456789";
        String expectedOtp = "123456";

        when(valueOperations.get(phoneNumber)).thenReturn(expectedOtp);

        String retrievedOtp = otpService.validateOtp(phoneNumber);

        assertNotNull(retrievedOtp);
        assertEquals(expectedOtp, retrievedOtp);
        verify(valueOperations).get(phoneNumber);
    }

}