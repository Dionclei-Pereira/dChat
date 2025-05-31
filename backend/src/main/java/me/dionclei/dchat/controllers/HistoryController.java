package me.dionclei.dchat.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.dchat.documents.Message;
import me.dionclei.dchat.services.interfaces.MessageService;

@RestController
@RequestMapping("/history")
public class HistoryController {
	
	private MessageService messageService;
	
	public HistoryController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("/global")
	public ResponseEntity<List<Message>> findAllGlobal() {
		return ResponseEntity.ok().body(messageService.findAll());
	}
}
