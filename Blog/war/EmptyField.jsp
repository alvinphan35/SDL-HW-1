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
  	<title>Alec and Alvin's Blog EmptyField</title>
  </head>

  <body>
  <p>Alec and Alvin's Blog  <img src="mountain.jpg"><p>
<p>Please give both a title and body for your post.<p>

<p>
<a href="post.jsp">Post</a>
</p>

<p>Return to
<a href="blog.jsp">blog</a>
</p>



  </body>
  
</html>