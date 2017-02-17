package blog;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostBlogServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        
        ObjectifyService.register(Post.class);
        if(user != null)
        {
        	if(title == "" || body == "")
        	{
        		resp.sendRedirect("/EmptyField.jsp?");
        	}
        	else
        	{
                Post post = new Post(user, title, body);
                ofy().save().entity(post).now();
                resp.sendRedirect("/blog.jsp?");
        	}
        }
        else
        {
        	resp.sendRedirect("/login.jsp?");
        }
    }
}