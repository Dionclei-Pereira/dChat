package me.dionclei.dchat.controllers;

import java.security.Principal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.dto.MessageDTO;
import me.dionclei.dchat.dto.PrivateMessageDTO;
import me.dionclei.dchat.services.interfaces.MessageService;

@Controller
public class ChatController {
	
	private MessageService messageService;
	private SimpMessagingTemplate template;
	
	public ChatController(MessageService messageService, SimpMessagingTemplate template) {
		this.messageService = messageService;
		this.template = template;
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

	@MessageMapping("/private")
	public void privateChat(PrivateMessageDTO messageDTO, Principal principal) {

		String receiver = messageDTO.to();

		Message message = new Message(UUID.randomUUID().toString(), 
			messageDTO.content(),
			Instant.now(),
			principal.getName());

		template.convertAndSendToUser(receiver, "/queue/messages", message);
	}
}
