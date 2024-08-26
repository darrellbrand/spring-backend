package com.djf.aibackend.security;

import com.djf.aibackend.model.UserData;
import com.djf.aibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {


    private final  UserRepository userRepository;

    @Autowired
    public ApiKeyService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;

    }
    public String createApiKey(String androidId) {
        String apiKey = ApiKeyGenerator.generateApiKey();
        UserData entity = new UserData();
        entity.setAndroidId(androidId);
        entity.setApiKey(apiKey);
        userRepository.save(entity);
        return apiKey;
    }

    public  boolean validateApiKey(String androidId, String apiKey) {
        UserData entity = userRepository.findById(Integer.valueOf(androidId)).orElse(null);
        return entity != null && entity.getApiKey().equals(apiKey);
    }


}