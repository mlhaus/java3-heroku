<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String,String> results = (Map)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String height = results.containsKey("height") ? results.get("height") : "";
    String weight = results.containsKey("weight") ? results.get("weight") : "";
    String heightFilled = results.containsKey("height") && !results.get("height").equals("") ? "filled" : "";
    String weightFilled = results.containsKey("weight") && !results.get("weight").equals("") ? "filled" : "";
    String bmi = results.containsKey("bmi") ? results.get("bmi") : "";
    String heightError = results.containsKey("heightError") ? results.get("heightError") : "";
    String weightError = results.containsKey("weightError") ? results.get("weightError") : "";

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Body Mass Index Calculator</title>
    <link rel="stylesheet" href="styles/bmi.css">
</head>
<body>
<div class="container">
    <h1>BMI Calculator</h1>
    <p>Enter your height and weight to calculate your body mass index</p>
    <form action="bmi" method="post" class="cool-form">
        <div class="input-container">
            <input type="text" class="<%= heightFilled %>" name="height" id="height" value="<%= height %>" />
            <label for="height">Height in inches</label>
        </div>
        <div class="input-container">
            <input type="text" class="<%= weightFilled %>" name="weight" id="weight" value="<%= weight %>" />
            <label for="weight">Weight in pounds</label>
        </div>
        <input type="submit" value="Calculate" />
    </form>
    <% if(!bmi.equals("")) { %>
    <p>Your BMI is <%= bmi %></p>
    <% } else { %>
        <% if(!heightError.equals("")) { %>
        <p><%= heightError %></p>
        <% } %>
        <% if(!weightError.equals("")) { %>
        <p><%= weightError %></p>
        <% } %>
    <% } %>
</div>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="scripts/bmi.js"></script>
</body>
</html>