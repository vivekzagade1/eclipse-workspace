package krishagni.messenger.service;

import java.util.ArrayList;
import java.util.List;

import krishagni.messenger.model.Message;

public class MessageService {
	
	public List<Message> getAllMessages()
	{
		Message m1 = new Message(1, "Hii", "Hrishi");
		Message m2 = new Message(1, "Hello", "Rahul");
		 List<Message> list = new ArrayList<>();
		 list.add(m1);
		 list.add(m2);
		 return list;
	}
}
