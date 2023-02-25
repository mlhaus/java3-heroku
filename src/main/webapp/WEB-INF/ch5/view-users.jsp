<%@ page import="com.hauschildt.ch5.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>)request.getAttribute("users");
    if(users == null) {
        users = new ArrayList<>();
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View All Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-2">
    <h1>All Users</h1>
    <p class="lead">There <%= users.size() == 1 ? "is" : "are" %> <%= users.size() %> user<%= users.size() != 1 ? "s" : "" %></p>
    <div class="row">
        <% for(User user: users) { %>
        <div class="col-md-6">
            <ul class="list-group mb-2">
                <li class="list-group-item active"><%= user.getFirst_name()%> <%= user.getLast_name() %></li>
                <li class="list-group-item">ID: <%= user.getId()%></li>
                <li class="list-group-item">Email: <%= user.getEmail()%></li>
                <li class="list-group-item">Phone: <%= user.getPhone()%></li>
                <li class="list-group-item">Password: <%= user.getPassword()%></li>
                <li class="list-group-item">Status: <%= user.getStatus() %></li>
            </ul>
        </div>
        <% } %>
    </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>