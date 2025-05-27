package me.dionclei.dchat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

}
