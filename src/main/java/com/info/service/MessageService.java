package com.info.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.info.database.DatabaseClass;
import com.info.exception.DataNotFoundException;
import com.info.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L, new Message(1, "Hello World", "kon"));
		messages.put(2L, new Message(2, "Hello Jersey", "san"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values() ) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
		
	}
	
	public List<Message> getAllMessagesPaginated(int start , int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		
		return list.subList(start, start+size);
	}
	
	
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id +  " not found ");
			
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <=0 ) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	 public Message removeMessage(long id) {
		 return messages.remove(id);
	 }

	}

		
