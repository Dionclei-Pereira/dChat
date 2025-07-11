package me.dionclei.dchat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.Chat;

public interface ChatRepository extends MongoRepository<Chat, String> {

}
