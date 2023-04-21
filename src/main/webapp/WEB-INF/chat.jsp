<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CHANGE THE TITLE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/chat.css">
</head>
<body>
<div class="container">
    <section id="messageContainer">
<%--  Websocket forms must be handled with JavaScript, not Servlets --%>
        <form id="messageForm">
            <fieldset>
                <legend>Chat with others</legend>
                <p>
                    <label for="userName">Your name:</label><br>
                    <input type="text" name="userName" id="userName">
                </p>
                <p>
                    <label for="message">Message:</label><br>
                    <input type="text" name="message" id="message" maxlength="280">
                </p>
                <input type="submit" value="Send">
                <span id="errorText" style="color:red;"></span>
            </fieldset>
        </form>
        <div id="messages"></div>
    </section>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script src="scripts/chat.js"></script>
</body>
</html>