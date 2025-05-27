package me.dionclei.dchat.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.dionclei.dchat.exceptions.TokenException;
import me.dionclei.dchat.repositories.dUserRepository;
import me.dionclei.dchat.services.interfaces.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	private TokenService service;
	private dUserRepository repository;
	
	public SecurityFilter(TokenService service, dUserRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request.getHeader("Authorization"));
		
		try {
			if (token != null) {
				var subject = service.validateToken(token);
				if (subject != null) {
					var user = repository.findByName(subject);
					var username = new UsernamePasswordAuthenticationToken(user.get().getName(), null, user.get().getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(username);
				}
			}
			
			filterChain.doFilter(request, response);
		} catch (TokenException e) {
			// handling the exception manually because it is inside a filter
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
	}
	
	private String getToken(String header) {
		if (header == null) return null;
		return header.trim().replace("Bearer ", "");
	}
}
