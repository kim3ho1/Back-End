package com.kim3ho1.yourprotein.gpt.config;


import com.kim3ho1.yourprotein.gpt.service.StreamCompletionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer {

  private final StreamCompletionHandler streamCompletionHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(streamCompletionHandler, "/chat/stream").setAllowedOrigins("*");
  }
}
