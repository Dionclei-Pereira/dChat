package me.dionclei.dchat.services.interfaces;

import java.util.Optional;

import me.dionclei.dchat.documents.Contact;

public interface ContactService {
    
    Optional<Contact> findById(String id);

    Optional<Contact> save(Contact contact);

}
