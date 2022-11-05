package br.com.quarkus_finance.dao.all;

import java.util.ArrayList;
import java.util.List;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = -5376009802745854729L;
	
	private List<String> messages = new ArrayList<>();
	
	public DAOException() {
	}
	
	public DAOException(String message) {
		super(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
