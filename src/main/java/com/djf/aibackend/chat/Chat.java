package com.djf.aibackend.chat;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class Chat {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    @Bean
    public OpenAiChatModel chatModel() {
        var openAiApi = new OpenAiApi(apiKey);
        var openAiChatOptions = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withTemperature(0.4f)
                .withMaxTokens(200)
                .build();
        return new OpenAiChatModel(openAiApi, openAiChatOptions);
    }}