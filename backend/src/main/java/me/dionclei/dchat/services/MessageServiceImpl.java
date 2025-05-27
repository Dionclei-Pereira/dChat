package me.dionclei.dchat.services;

import org.springframework.beans.factory.annotation.Autowired;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.repositories.MessageRepository;
import me.dionclei.dchat.services.interfaces.MessageService;

public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository repository;
	 
	public Message save(Message message) {
		return repository.save(message);
	}
}
