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
import me.dionclei.dchat.services.interfaces.ChatService;
import me.dionclei.dchat.services.interfaces.ContactService;
import me.dionclei.dchat.services.interfaces.MessageService;
import me.dionclei.dchat.utils.ContactIdGenerator;

@Controller
public class ChatController {
	
	private MessageService messageService;
	private SimpMessagingTemplate template;
	private ContactService contactService;
	private ChatService chatService;
	
	public ChatController(MessageService messageService, SimpMessagingTemplate template,
			ContactService contactService, ChatService chatService) {
		this.messageService = messageService;
		this.template = template;
		this.contactService = contactService;
		this.chatService = chatService;
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
		
		String id = ContactIdGenerator.generateId(principal.getName(), receiver);
		var contact = contactService.findById(id);
		
		if (contact.isPresent() && contact.get().getAccepted() == true) {
			chatService.save(message, id);
			template.convertAndSendToUser(receiver, "/queue/messages", message);
		}
	}
}
