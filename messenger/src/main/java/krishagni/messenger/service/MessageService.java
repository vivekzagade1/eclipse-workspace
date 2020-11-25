package krishagni.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import krishagni.messenger.database.DatabaseClass;
import krishagni.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L, new Message(1, "Hiiii", "Hrishi"));
		messages.put(2L, new Message(2, "Hello", "Vivek"));
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message> (messages.values());
	}
	
	public Message getMessage(long id)
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		if(message.getId() <= 0)
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public void removeMessage(long id)
	{
		messages.remove(id);
	}
	
}
