package me.dionclei.dchat.controllers;

import java.security.Principal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.dto.MessageDTO;
import me.dionclei.dchat.services.interfaces.MessageService;

@Controller
public class ChatController {
	
	private MessageService messageService;
	
	public ChatController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@MessageMapping("/global")
	@SendTo("/topic/messages")
	public Message global(MessageDTO messageDTO, Principal principal) {
		
		Message message = new Message(UUID.randomUUID().toString(), 
				messageDTO.content(), 
				Instant.now(), 
				principal.getName());
		
		messageService.save(message);
		
		return message;
	}
}
