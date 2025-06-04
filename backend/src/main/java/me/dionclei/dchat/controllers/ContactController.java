package me.dionclei.dchat.controllers;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.dchat.documents.Contact;
import me.dionclei.dchat.dto.ContactRequest;
import me.dionclei.dchat.repositories.ContactRepository;
import me.dionclei.dchat.services.interfaces.ContactService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }
    
    @PostMapping("/send")
    public ResponseEntity<Void> sendRequest(@RequestBody ContactRequest request, Principal principal) {
        String id = generateId(principal.getName(), request.name());

        var contact = service.findById(id);

        if (contact.isEmpty()) {
            Contact newContact = new Contact();
            
            newContact.setId(id);
            newContact.setAccepted(false);
            newContact.setFrom(principal.getName());

            service.save(newContact);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptRequest(@RequestBody ContactRequest request, Principal principal) {
        String id = generateId(principal.getName(), request.name());
        var optionalContact = service.findById(id);

        if(optionalContact.isPresent()) {

            var contact = optionalContact.get();

            if (contact.getFrom() != principal.getName()) {
                contact.setAccepted(true);

                service.save(contact);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    private String generateId(String from, String to) {
        String[] names = { from, to };
        Arrays.sort(names);
        String id = names[0] + "-" + names[1];
        return id;
    }
}
