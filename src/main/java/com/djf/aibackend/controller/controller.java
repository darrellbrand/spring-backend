package com.djf.aibackend.controller;

import com.djf.aibackend.remote.GenerateResponse;
import com.djf.aibackend.remote.RegisterResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class controller {

    private final OpenAiChatModel chatModel;

    @Autowired
    public controller(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerateResponse> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        GenerateResponse generateResponse = new GenerateResponse();
        generateResponse.setGenerate(chatModel.call(message));
        return ResponseEntity.ok(generateResponse);
    }

    @RequestMapping("/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}