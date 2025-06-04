package me.dionclei.dchat.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contact {
    
    @Id
    private String id;

    private Boolean accepted;

    private String from;

    public Contact() {}

    public Contact(String id, Boolean accepted) {
        this.accepted = accepted;
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

    /**
    * use the name - anotherName ordered syntax to save an contact
    */
    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAccepted() {
        return this.accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
