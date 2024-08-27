package com.djf.aibackend.security;

import com.djf.aibackend.model.UserData;
import com.djf.aibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ApiKeyService {
    @Value("${api-key-temp}")
    private String apiKeyTemp;

    private final  UserRepository userRepository;

    @Autowired
    public ApiKeyService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;

    }
    public String createApiKey(String androidId) {
        UserData entityOld = userRepository.findByAndroidId(androidId);
        if(entityOld != null) {
            return entityOld.getApiKey();
        }
        String apiKey = ApiKeyGenerator.generateApiKey();
        UserData entity = new UserData();
        entity.setAndroidId(androidId);
        entity.setApiKey(apiKey);
        userRepository.save(entity);
        return apiKey;
    }

    public  boolean validateApiKey(String androidId, String apiKey) {

        UserData entity = userRepository.findByAndroidId(androidId);
        return entity != null && entity.getApiKey().equals(apiKey);
    }


}