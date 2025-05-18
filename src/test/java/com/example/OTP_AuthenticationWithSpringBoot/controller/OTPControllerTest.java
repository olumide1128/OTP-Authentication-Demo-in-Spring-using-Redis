package com.example.OTP_AuthenticationWithSpringBoot.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.OTP_AuthenticationWithSpringBoot.service.OTPService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

class OTPControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OTPService otpService;

    @InjectMocks
    private OTPController otpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(otpController).build();
    }

    @Test
    void testGenerateOTP() throws Exception {
        String phoneNumber = "+123456789";
        String expectedOtp = "123456";

        when(otpService.generateOtp(phoneNumber)).thenReturn(expectedOtp);

        mockMvc.perform(post("/auth/generate")
                        .param("phoneNumber", phoneNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("OTP sent successfully!"));

        verify(otpService).generateOtp(phoneNumber);
    }
}