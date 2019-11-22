package org.polytech.pfe.domego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer, WebMvcConfigurer {

    private final SocketHandler roomSocket;

    @Autowired
    public WebSocketConfig(SocketHandler roomSocket) {
        this.roomSocket = roomSocket;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(roomSocket, "/name").setAllowedOrigins("*");
        registry.addHandler(roomSocket, "/name").setAllowedOrigins("*").withSockJS();
        registry.addHandler(roomSocket, "/room").setAllowedOrigins("*");
        registry.addHandler(roomSocket, "/room").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

}