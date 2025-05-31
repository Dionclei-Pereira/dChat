package me.dionclei.dchat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.repositories.MessageRepository;
import me.dionclei.dchat.services.interfaces.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository repository;
	 
	public Message save(Message message) {
		return repository.save(message);
	}
	
	public List<Message> findAll() {
		return repository.findAll();
	}
}
