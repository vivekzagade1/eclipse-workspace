package krishagni.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import krishagni.messenger.model.Message;
import krishagni.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getAllMessages(@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size) {
		if(year > 0)
		{
			return messageService.getAllMessageForYear(year);
		}
		if(size > 0 && start > 0)
		{
			return messageService.getAllMessagePaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Message addMessage(Message message) 
	{
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message)
	{
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	public void deleteMessage(@PathParam("messageId") long id)
	{
		messageService.removeMessage(id);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id)
	{
		return messageService.getMessage(id);
	}

}
