package me.dionclei.dchat.services.interfaces;

import java.util.Optional;

import me.dionclei.dchat.documents.dUser;
/**
 * Interface to manage the dUsers from the application
 */
public interface UserService {
	
	Optional<dUser> findByName(String name);
	
	void createUser(String name, String password);
	
}
