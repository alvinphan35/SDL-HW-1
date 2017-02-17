//http://guestbook-156616.appspot.com/ofyguestbook.jsp

package guestbook;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfySignGuestbookServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String content = req.getParameter("content");
        
        ObjectifyService.register(Greeting.class);
        Greeting greeting = new Greeting(user, content);
        ofy().save().entity(greeting).now();
        
        resp.sendRedirect("/ofyguestbook.jsp?");
    }
}