package me.dionclei.dchat.services;

import java.util.List;
import java.util.Optional;

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
		
	    long totalMessages = repository.count();
	    
	    if (totalMessages >= 40) {
	        Optional<Message> oldest = repository.findFirstByOrderByMomentAsc();
	        oldest.ifPresent(repository::delete);
	    }
	    
		return repository.save(message);
	}
	
	public List<Message> findAll() {
		return repository.findAll();
	}
}
