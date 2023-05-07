<%@ page import="java.time.*" %>
<%@ page import="java.time.format.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    // Declaration of today's date and time
    ZonedDateTime today = ZonedDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    String currentDate = today.format(dateFormatter);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    String currentTime = today.format(timeFormatter);
    // No conditional statements allowed here
%>
<%
    // This is a scriplet, you can use conditional statements here
    Map<String,String> results = (Map<String, String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String firstNumber = results.containsKey("firstNumber") ? results.get("firstNumber") : "";
    String secondNumber = results.containsKey("secondNumber") ? results.get("secondNumber") : "";
    String firstNumberError = results.containsKey("firstNumberError") ? results.get("firstNumberError") : "";
    String firstNumberInvalid = results.containsKey("firstNumberError") ? "is-invalid" : "";
    String secondNumberError = results.containsKey("secondNumberError") ? results.get("secondNumberError") : "";
    String secondNumberInvalid = results.containsKey("secondNumberError") ? "is-invalid" : "";
    String sum = results.containsKey("sum") ? results.get("sum") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Adding App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-6">
            <h1>Add</h1>
            <p class="lead">Enter two numbers and press submit to calculate the sum.</p>
            <form action="add" method="post">
                <div class="form-group mb-2">
                    <label for="firstNumber">First Number:</label>
                    <input type="text" class="form-control <%= firstNumberInvalid %>" id="firstNumber" name="firstNumber" value="<%= firstNumber %>">
                    <% if(!firstNumberError.isEmpty()) { %>
                    <div class="invalid-feedback"><%= firstNumberError %></div>
                    <% } %>
                </div>
                <div class="form-group mb-2">
                    <label for="secondNumber">Second Number:</label>
                    <input type="text" class="form-control <%= secondNumberInvalid %>" id="secondNumber" name="secondNumber" value="<%= secondNumber %>">
                    <% if(!secondNumberError.isEmpty()) { %>
                    <div class="invalid-feedback"><%= secondNumberError %></div>
                    <% } %>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </div><%-- end col --%>
        <% if(results.containsKey("sum")) { %>
        <p class="mt-2"><%= firstNumber %> + <%= secondNumber %> = <%= sum %></p>
        <% } else { %>
        <p class="mt-2">Current date/time: <%= currentDate %> <%= currentTime %></p>
        <% } %>
    </div><%-- end row --%>
</div><%-- end container --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>