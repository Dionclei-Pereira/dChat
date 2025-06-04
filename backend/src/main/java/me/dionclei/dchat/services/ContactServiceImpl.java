package me.dionclei.dchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dionclei.dchat.documents.Contact;
import me.dionclei.dchat.repositories.ContactRepository;
import me.dionclei.dchat.services.interfaces.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    public Optional<Contact> findById(String id) {
        return repository.findById(id);
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> findAll(String name) {
        return repository.findByIdContainingAndAcceptedIsTrue(name);
    }
}
