<%@ page import="com.hauschildt.ch5and6.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    HttpSession thisSession = request.getSession();
    User user = (User)thisSession.getAttribute("user");
    if(user == null) {
        user = new User();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Table of Contents</h1>
<h3>Chapters 3 and 4</h3>
<ul>
    <li><a href="add">Adding App</a></li>
    <li><a href="temp">Temperature Converter</a></li>
    <li><a href="bmi">Body Mass Index App</a></li>
    <li><a href="messaging">Messaging App</a></li>
    <li><a href="countries">Countries App</a></li>
    <li><a href="artist">Spotify App</a></li>
</ul>
<h3>Chapter 5 and 6</h3>
<ul>
    <% if(user.getPrivileges().equals("admin")) { %>
    <li><a href="view-users">View all users</a></li>
    <% } %>
    <% if(!user.getStatus().equals("active")) { %>
    <li><a href="register">Register user</a></li>
    <li><a href="login">Login</a></li>
    <% } else { %>
    <li><a href="manage-profile">View Account</a></li>
    <li><a href="logout">Logout</a></li>
    <% } %>
</ul>
<h3>Chapter 10</h3>
<ul>
    <li><a href="chat">Chat Websocket</a></li>
</ul>
</body>
</html>