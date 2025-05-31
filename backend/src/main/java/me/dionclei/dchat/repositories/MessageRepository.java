package me.dionclei.dchat.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
	
	Optional<Message> findFirstByOrderByMomentAsc();
	
}
