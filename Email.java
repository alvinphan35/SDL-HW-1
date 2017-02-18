package blog;

import java.util.Date;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Email{
	@Id Long id;
	User user;
	String email;
	
	private Email(){}
	
	public Email(User user, String email){
		this.user = user;
		this.email = email();
	}
	public User getUser(){
		return user;
	}
}