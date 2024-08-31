package com.djf.aibackend.controller;

import com.djf.aibackend.remote.RegisterResponse;
import com.djf.aibackend.security.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final ApiKeyService apiKeyService;
    private static final String AUTH_ANDROID_ID = "x-android-id";
    @Autowired
    public AuthController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @GetMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestHeader(AUTH_ANDROID_ID) String androidId) {
      RegisterResponse registerResponse = new RegisterResponse();
      registerResponse.setApiKey(apiKeyService.createApiKey(androidId));
      return ResponseEntity.ok(registerResponse);
    }
}
