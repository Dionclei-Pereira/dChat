package me.dionclei.dchat.services.interfaces;

import me.dionclei.dchat.documents.dUser;

/**
 * Interface that manages the token providers and validation
 */
public interface TokenService {
	
	/**
	 * 
	 * Method to validate a token
	 * 
	 * @param token
	 * @return if the token is valid in a boolean
	 */
	Boolean isValid(String token);
	
	/**
	 * 
	 * Method to get the subject from a token, the main subject is the name of the user
	 * 
	 * @param token
	 * @return the user's name
	 */
	String validateToken(String token);
	
	/**
	 * 
	 * Generates a new token mapped by an user, the token's subject is the name
	 * 
	 * @param user an user of the application
	 * @return a new token
	 */
	String generateToken(dUser user);
}
