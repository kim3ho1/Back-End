package com.kim3ho1.yourprotein.gpt.controller;


import com.kim3ho1.yourprotein.gpt.dto.request.GPTCompletionChatRequest;
import com.kim3ho1.yourprotein.gpt.dto.request.GPTCompletionRequest;
import com.kim3ho1.yourprotein.gpt.dto.response.CompletionChatResponse;
import com.kim3ho1.yourprotein.gpt.dto.response.CompletionResponse;
import com.kim3ho1.yourprotein.gpt.service.GPTChatRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/chatgpt/rest")
@RequiredArgsConstructor
public class ChatGPTRestController {

  private final GPTChatRestService gptChatRestService;

  @PostMapping("/completion/chat")
  public CompletionChatResponse chat(final @RequestBody HashMap<String, String> prompt) {
    return completionChat(new GPTCompletionChatRequest("gpt-3.5-turbo", "user", prompt.get("prompt"), 1000));
  }


  public CompletionChatResponse completionChat(final GPTCompletionChatRequest gptCompletionChatRequest) {

    return gptChatRestService.completionChat(gptCompletionChatRequest);
  }

}
