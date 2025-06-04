package me.dionclei.dchat.controllers;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.RpcClient.Response;

import me.dionclei.dchat.documents.Contact;
import me.dionclei.dchat.dto.ContactRequest;
import me.dionclei.dchat.repositories.ContactRepository;
import me.dionclei.dchat.services.interfaces.ContactService;
import me.dionclei.dchat.services.interfaces.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;
    private UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }
    
    @PostMapping("/send")
    public ResponseEntity<Void> sendRequest(@RequestBody ContactRequest request, Principal principal) {
        String id = generateId(principal.getName(), request.name());

        var user = userService.findByName(request.name());
        var contact = contactService.findById(id);

        if (contact.isEmpty() && user.isPresent()) {
            Contact newContact = new Contact();
            
            newContact.setId(id);
            newContact.setAccepted(false);
            newContact.setFrom(principal.getName());

            contactService.save(newContact);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptRequest(@RequestBody ContactRequest request, Principal principal) {
        String id = generateId(principal.getName(), request.name());
        var optionalContact = contactService.findById(id);

        if(optionalContact.isPresent()) {

            var contact = optionalContact.get();

            if (contact.getFrom() != principal.getName()) {
                contact.setAccepted(true);

                contactService.save(contact);

                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<Contact>> findAll(Principal principal) {
        return ResponseEntity.ok().body(contactService.findAll(principal.getName()));
    }

    private String generateId(String from, String to) {
        String[] names = { from, to };
        Arrays.sort(names);
        String id = names[0] + "-" + names[1];
        return id;
    }
}
