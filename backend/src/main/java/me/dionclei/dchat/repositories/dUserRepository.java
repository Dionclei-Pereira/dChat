package me.dionclei.dchat.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.dUser;

public interface dUserRepository extends MongoRepository<dUser, String>{
	
	Optional<dUser> findByName(String name);
	
}
