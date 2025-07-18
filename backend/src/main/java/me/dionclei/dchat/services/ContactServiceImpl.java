package me.dionclei.dchat.services;

import java.util.ArrayList;
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

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> findAll(String name) {
        var contacts = repository.findByIdContainingAndAcceptedIsTrue(name);
        contacts.removeIf(c -> { 
        		var names = c.getId().split("-");
        		if (!names[0].equals(name) && !names[1].equals(name)) {
        			return true;
        		}
        		return false;
        	});
        return contacts;
    }

    public List<Contact> findAllRequests(String name) {
        var requests = repository.findByIdContainingAndAcceptedIsFalse(name);

        List<Contact> received = new ArrayList<>();
        requests.forEach(c -> {
        	var names = c.getId().split("-");
            if (!c.getFrom().contains(name) && (names[0].equals(name) || names[1].equals(name))) {
                received.add(c);
            }
        });
        return received;
    }
}
