package krishagni.messenger.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
	
	private long id;
	private String author;
	private Date created;
	private String message;
	private Map<Long, Comment> comments  = new HashMap<>();
	
	public Message()
	{
		
	}
	
	public Message(long id, String message, String author) 
	{
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}
	
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}
	public Date getCreated() 
	{
		return created;
	}
	public void setCreated(Date created) 
	{
		this.created = created;
	}
	public String getAuthor() 
	{
		return author;
	}
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	
	@XmlTransient    //Ignore XML and JSON representation of the "comments" in "Messages"
	public Map<Long, Comment> getComments()
	{
		return comments;
	}
	
	public void setComments(Map<Long, Comment> comments)
	{
		this.comments = comments;
	}
	
	
}
