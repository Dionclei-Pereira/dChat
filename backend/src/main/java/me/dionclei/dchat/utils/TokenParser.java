package me.dionclei.dchat.utils;

public class TokenParser {
	
	public static String getToken(String token) {
		if (token == null) return null;
		token = token.trim();
		if (token.startsWith("Bearer ")) {
			token = token.substring(0, 7);
		}
		return token;
	}
}
