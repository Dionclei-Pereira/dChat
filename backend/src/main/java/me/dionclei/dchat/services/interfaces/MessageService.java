package me.dionclei.dchat.services.interfaces;

import java.util.List;

import me.dionclei.dchat.documents.Message;

public interface MessageService {
	
	Message save(Message message);
	
	List<Message> findAll();
}
