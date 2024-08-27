package com.djf.aibackend.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final ApiKeyService apiKeyService;

    @Autowired
    public AuthenticationService(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    private static final String AUTH_TOKEN_HEADER_NAME = "x-api-key";
    private static final String AUTH_ANDROID_ID = "x-android-id";

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        String androidId = request.getHeader(AUTH_ANDROID_ID);
        if (apiKey == null || androidId == null || !apiKeyService.validateApiKey(androidId, apiKey)) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
