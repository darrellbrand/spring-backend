package com.djf.aibackend.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class ApiKeyHolder {


    private static String localKey;

    @Value("${api.key}")
    public void setLocalKey(String localKey) {
        ApiKeyHolder.localKey = localKey;
    }
    public static String getLocalKey() {
        return localKey;
    }
}