package blog;

import java.util.Date;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Post implements Comparable<Post>{
	@Id Long id;
	User user;
	String title;
	String body;
	Date date;
	
	private Post(){}
	
	public Post(User user, String title, String body){
		this.user = user;
		this.title = title;
		this.body = body;
		date =new Date();
	}
	
	public User getUser(){
		return user;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getBody()
	{
		return body;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	@Override
	public int compareTo(Post other)
	{
		if(date.after(other.date)){
			return 1;
		}
		else if(date.before(other.date)){
			return -1;
		}
		return 0;
	}
}
