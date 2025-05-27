package me.dionclei.dchat.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dionclei.dchat.documents.dUser;
import me.dionclei.dchat.enums.UserRole;
import me.dionclei.dchat.repositories.dUserRepository;
import me.dionclei.dchat.services.interfaces.UserService;

/**
 * Main implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {
	
	private dUserRepository userRepository;

	
	public Optional<dUser> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public void createUser(String name, String password, UserRole role) {
		dUser user = new dUser();
		user.setId(UUID.randomUUID().toString());
		user.setName(name);
		user.setPassword(password);
		user.setRole(role);
		
		userRepository.save(user);
	}
}
