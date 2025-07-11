package me.dionclei.dchat.services.interfaces;

import java.util.List;

import me.dionclei.dchat.documents.Message;

public interface ChatService {
	
	void save(Message message, String id);
	
	List<Message> findAll(String id);
}
