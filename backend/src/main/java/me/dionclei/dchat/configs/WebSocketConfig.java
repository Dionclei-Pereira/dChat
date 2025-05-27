package me.dionclei.dchat.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Autowired
	private WebSocketFilter filter;
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(filter);
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableStompBrokerRelay("/topic", "/queue")
			.setRelayHost("rabbitmq")
			.setRelayPort(61613)
			.setClientLogin("guest")
			.setClientPasscode("guest");
		config.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry config) {
		config.addEndpoint("/ws")
			.setAllowedOriginPatterns("*")
			.withSockJS();
	}
}
