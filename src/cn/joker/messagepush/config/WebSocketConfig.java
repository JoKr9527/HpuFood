package cn.joker.messagepush.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import cn.joker.messagepush.handler.StoreAdminWebSocketHandler;
import cn.joker.messagepush.handler.UserWebSocketHandler;
import cn.joker.messagepush.interceptor.StoreAdminHandshakeInterceptor;
import cn.joker.messagepush.interceptor.UserHandshakeInterceptor;


@Configuration
@EnableWebSocket
@ComponentScans(value= {@ComponentScan(basePackages="cn.joker.messagepush.service,cn.joker.messagepush.daoImpl") })
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(userHandler(), "/messagepush/user").addInterceptors(userHandshakeInterceptor());
		registry.addHandler(storeHandler(), "/messagepush/store").addInterceptors(storeAdminHandshakeInterceptor());
	}
	@Bean
	public WebSocketHandler userHandler() {
		return new UserWebSocketHandler();
	}
	@Bean
	public HandshakeInterceptor userHandshakeInterceptor() {
		return new UserHandshakeInterceptor();
	}
	@Bean
	public WebSocketHandler storeHandler() {
		return new StoreAdminWebSocketHandler();
	}
	@Bean
	public HandshakeInterceptor storeAdminHandshakeInterceptor() {
		return new StoreAdminHandshakeInterceptor();
	}
}
