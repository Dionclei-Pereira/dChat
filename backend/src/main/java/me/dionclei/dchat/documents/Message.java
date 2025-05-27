package me.dionclei.dchat.documents;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Default implementation of a generic message, can be used to send global messages.
 */
@Document
public class Message {
	
	@Id
	private String id;
	private String content;
	private Instant moment;
	
	//Name of the user that is sending the message
	private String from;
	
	public Message() {}
	
	public Message(String id, String content, Instant moment, String from) {
		super();
		this.id = id;
		this.content = content;
		this.moment = moment;
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	/**
	 * 
	 * @return The name of the user that is sending the message
	 */
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
