package me.dionclei.dchat.services.interfaces;

import java.util.List;
import java.util.Optional;

import me.dionclei.dchat.documents.Contact;

public interface ContactService {
    
    Optional<Contact> findById(String id);

    Contact save(Contact contact);

    List<Contact> findAll(String name);

    List<Contact> findAllRequests(String name);

    void delete(String id);
}
