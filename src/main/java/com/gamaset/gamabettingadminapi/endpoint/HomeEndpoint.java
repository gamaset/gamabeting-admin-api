package com.gamaset.gamabettingadminapi.endpoint;

import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeEndpoint {

	@GetMapping("/home")
	public Message home() {
		return new Message("Hello World");
	}

}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	public Message(String content) {
		this.content = content;
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

}