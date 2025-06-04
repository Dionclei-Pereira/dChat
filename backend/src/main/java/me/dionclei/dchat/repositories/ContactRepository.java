package me.dionclei.dchat.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.dionclei.dchat.documents.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
    
    List<Contact> findByIdContainingAndAcceptedIsFalse(String name);

    List<Contact> findByIdContainingAndAcceptedIsTrue(String name);
}
