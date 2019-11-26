package org.polytech.pfe.domego;

import org.polytech.pfe.domego.services.sockets.game.GameSocketHandler;
import org.polytech.pfe.domego.services.sockets.room.RoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
//@EnableWebMvc
public class WebSocketConfig implements WebSocketConfigurer, WebMvcConfigurer {

    private final RoomSocketHandler roomSocket;
    private final GameSocketHandler gameSocket;

    @Autowired
    public WebSocketConfig(RoomSocketHandler roomSocket, GameSocketHandler gameSocket) {
        this.roomSocket = roomSocket;
        this.gameSocket = gameSocket;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(roomSocket, "/room").setAllowedOrigins("*");
        registry.addHandler(roomSocket, "/room").setAllowedOrigins("*").withSockJS();
        registry.addHandler(gameSocket, "/game").setAllowedOrigins("*");
        registry.addHandler(gameSocket, "/game").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

}