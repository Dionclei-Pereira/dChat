package me.dionclei.dchat.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
    
    Optional<Contact> findByIdContaining(String bothNames);

    List<Contact> findByIdContainingAndAcceptedIsTrue(String name);
}
