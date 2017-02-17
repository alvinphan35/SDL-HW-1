<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.Collections" %>
<%@ page import="blog.Post" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="com.googlecode.objectify.Objectify" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<html>
  <head>
  	<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  	<title>Alec and Alvin's Blog</title>
  </head>

  <body>
  <p>Alec and Alvin's Blog  <img src="mountain.jpg"><p>
<%
    String blogName = request.getParameter("blogName");
    if (blogName == null) {
        blogName = "Alec's Blog";
    }
    pageContext.setAttribute("blogName", blogName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      pageContext.setAttribute("user", user);
%>
<p>Hello, ${fn:escapeXml(user.nickname)}! (
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign out</a>)</p>
<%
    } else {
%>
<p>Hello!
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
to post.</p>
<%
    }
%>

	<p>Create a new 
	<a href="post.jsp">post</a>
	</p>
	
	<p> 
	<a href="history.jsp">List All Posts</a>
	</p>

<%
	ObjectifyService.register(Post.class);
    List<Post> posts = ObjectifyService.ofy().load().type(Post.class).list();
    Collections.sort(posts);
    if (posts.isEmpty()) {
        %>
        <p>Blog '${fn:escapeXml(blogName)}' has no messages.</p>
        <%
    } else {
        for (int i = posts.size() - 1; i > posts.size() - 6; i--) {
        	Post post = posts.get(i);
            pageContext.setAttribute("post_body",
                                     post.getBody());
			pageContext.setAttribute("post_user", post.getUser());
			pageContext.setAttribute("post_title", post.getTitle());
			pageContext.setAttribute("post_date", post.getDate());
                %>
            <blockquote>${fn:escapeXml(post_title)}</blockquote>
            <blockquote>${fn:escapeXml(post_body)}</blockquote>
            <p><b>${fn:escapeXml(post_user.nickname)} at ${fn:escapeXml(post_date)}</b></p>
            <%
        }
    }
%>

  </body>
  
</html>