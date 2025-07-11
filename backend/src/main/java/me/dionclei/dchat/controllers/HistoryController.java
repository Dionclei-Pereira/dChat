package me.dionclei.dchat.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.services.interfaces.ChatService;
import me.dionclei.dchat.services.interfaces.MessageService;
import me.dionclei.dchat.utils.ContactIdGenerator;

@RestController
@RequestMapping("/history")
public class HistoryController {
	
	private MessageService messageService;
	private ChatService chatService;
	
	public HistoryController(MessageService messageService, ChatService chatService) {
		this.messageService = messageService;
		this.chatService = chatService;
	}

	@GetMapping("/global")
	public ResponseEntity<List<Message>> findAllGlobal() {
		return ResponseEntity.ok().body(messageService.findAll());
	}
	
	@GetMapping("/private/{to}")
	public ResponseEntity<List<Message>> findAllPrivate(@PathVariable String to, Principal principal) {
		String id = ContactIdGenerator.generateId(principal.getName(), to);
		List<Message> messages = chatService.findAll(id);
		return ResponseEntity.ok().body(messages);
	}
}
