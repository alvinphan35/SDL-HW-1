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
  	<title>Alec and Alvin's Blog Post</title>
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
    <form action="/post" method="post">
  	  <p>Title of post<p>
      <div><textarea name="title" rows="1" cols="60"></textarea></div>
      <p>Body of post<p>
      <div><textarea name="body" rows="5" cols="60"></textarea></div>
      <div><input type="submit" value="Post" /></div>
      <input type="hidden" name="blogName" value="${fn:escapeXml(blogName)}"/>
    </form>
    
 	<p>Return to
	<a href="blog.jsp">blog</a>
	</p>

  </body>
  
</html>