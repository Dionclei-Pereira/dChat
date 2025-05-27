package me.dionclei.dchat.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import me.dionclei.dchat.documents.dUser;
import me.dionclei.dchat.exceptions.TokenException;
import me.dionclei.dchat.services.interfaces.TokenService;

@Service
public class TokenServiceHMAC implements TokenService {
	
	private String key = "2d0dfbccadc34b7472bec01da6cda224c3341f5fdf5246957f5a71a18fa39457";
	
	public Boolean isValid(String token) {
		try {
	        Algorithm algorithm = Algorithm.HMAC256(key);
	        JWTVerifier verifier = JWT.require(algorithm)
	                .withIssuer("URL-BACKEND")
	                .build();
	        
	        DecodedJWT jwt = verifier.verify(token);
	        return jwt.getSubject() != null && !jwt.getSubject().isEmpty();
		} catch (Exception e) {
			throw new TokenException(e.getMessage());
		}
	}

	public String validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(key);
		try {
			return JWT.require(algorithm)
					.withIssuer("dChat-Backend")
					.build()
					.verify(token)
					.getSubject();
		} catch (Exception e) {
			throw new TokenException(e.getMessage());
		}
	}

	public String generateToken(dUser user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(key);
			return JWT.create()
				.withIssuer("dChat-Backend")
				.withSubject(user.getName())
				.withExpiresAt(generateExpiresAt())
				.sign(algorithm);
		} catch (Exception e) {
			throw new TokenException(e.getMessage());
		}
	}
	
	private Instant generateExpiresAt() {
		return LocalDateTime.now().plusDays(3).toInstant(ZoneOffset.UTC);
	}
}
