package me.dionclei.dchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dionclei.dchat.documents.Chat;
import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.repositories.ChatRepository;
import me.dionclei.dchat.services.interfaces.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;
	
	public void save(Message message, String id) {
		Chat chat;
		Optional<Chat> chatOptional = chatRepository.findById(id);
		if (chatOptional.isPresent()) {
			chat = chatOptional.get();
			chat.addMessage(message);
			chatRepository.save(chat);
			return;
		}
		chat = new Chat(id, List.of(message));
		chatRepository.save(chat);
	}

	public List<Message> findAll(String id) {
		Optional<Chat> chatOptional = chatRepository.findById(id);
		if (chatOptional.isPresent()) {
			return chatOptional.get().getMessages();
		}
		return List.of();
	}
}
