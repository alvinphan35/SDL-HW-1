CroNServlet.java

package blog;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class CroNServlet extends HttpServlet {
private static final Logger _logger = Logger.getLogger(Emailing.class.getName());
public void doGet(HttpServletRequest req, HttpServletResponse resp)
throws IOException {
try {
_logger.info("Cron Job has been executed");
//GO through Objects of Emails and Send
//BEGIN

https://github.com/sendgrid/sendgrid-google-java/blob/master/README.md
Sendgrid mail  = new Sendgrid ("<sendgrid_username>","<sendgrid_password>");
mail.use_headers = false;
 
mail.setTo("foo@bar.com")
    .setFrom("me@bar.com")
    .setSubject("Subject goes here")
    .setText("Hello World!")
    .setHtml("<strong>Hello World!</strong>");

//END 
}
catch (Exception ex) {
//Log any exceptions in your Cron Job
}
}
@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
doGet(req, resp);
}
}

cron.xml

<?xml version="1.0" encoding="UTF-8"?>
<cronentries>
<cron>
<url>/SendMailLate</url>
<description>Send Emails out</description>
<schedule>every day 17:00</schedule>
<timezone>America/Chicago</timezone>
</cron>
</cronentries>

web.xml

  <servlet>
    <servlet-name> CroNServlet </servlet-name>
    <servlet-class>path to the servlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
	<servlet-name>CroNServlet </servlet-name>
	<url-pattern>/SendMailLate</url-pattern>
  </servlet-mapping>

Email.java
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

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailHolderServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String address = user.getEmail();
        
        ObjectifyService.register(Email.class);
        Email email = new Email(user, address);
        ofy().save().entity(greeting).now();
        
        resp.sendRedirect("/emailguestbook.jsp?");
    }
}
