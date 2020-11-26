package krishagni.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import krishagni.messenger.model.Message;
import krishagni.messenger.resources.beans.MessageFilterBeans;
import krishagni.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBeans filterBean ) {
		if(filterBean.getYear() > 0)
		{
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getSize() > 0 && filterBean.getStart() > 0)
		{
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) 
	{
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());                 // to get the ID of he message just posted to t=be added to complete Unified resource identifier
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();    // attach  the absolute path to the  message Id
		return Response.created(uri)										//"created gives the status code 201 as a response and the header is set to URI
					   .entity(newMessage)								
					   .build();											//converted to a response object					
		//return messageService.addMessage(message);
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
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
}
