package me.dionclei.dchat.configs;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import me.dionclei.dchat.exceptions.TokenException;
import me.dionclei.dchat.repositories.dUserRepository;
import me.dionclei.dchat.services.interfaces.TokenService;
import me.dionclei.dchat.utils.TokenParser;

@Component
public class WebSocketFilter implements ChannelInterceptor {
	
	private TokenService service;
	private dUserRepository repository;
	
	public WebSocketFilter(TokenService service, dUserRepository repository) {
		this.service = service;
		this.repository = repository;
	}
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		
		if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
			String token = accessor.getFirstNativeHeader("Authorization");
			if (token != null) {
				
				token = TokenParser.getToken(token);
				
				try {
					String subject = service.validateToken(token);
					
					if (subject != null) {
						var user = repository.findByName(subject);
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.get().getName(), null, user.get().getAuthorities());
						
						accessor.setUser(auth);
					}
				} catch (Exception e) {
					throw new TokenException("Invalid token");
				}
			}
		}
		
		return message;
	}
	
}
