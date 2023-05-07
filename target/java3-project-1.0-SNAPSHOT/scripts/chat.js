// STEP 7: Establish the websocket endpoint
const wsUri = "ws://" + document.location.host + document.location.pathname + "/chatEndpoint";
const websocket = new WebSocket(wsUri);
// STEP 8: Define what happens in the browser when an endpoint event occurs
websocket.onopen = function(event) {
    // alert("You are connected to the websocket");
};
websocket.onclose = function(event) {
    // alert("You are about to close the websocket");
};
websocket.onmessage = function(event) {
    //STEP 8b: Define what happens when you receive data from someone else
    updateTextArea(event.data, "in");
};
websocket.onerror = function(event) {

};

function displayError(msg) {
    var errdiv = document.getElementById("errorText");
    errdiv.innerHTML = msg;
}

/*
 * Send the message to the server endpoint and log the activity to the console
 */
function sendText(json) {
    console.log("sending text: " + json);
    // STEP 5b: Check that the websocket is open before sending the json
    if(isOpen(websocket)) {
        websocket.send(json);
    }
}

function isOpen(websocket) {
    return websocket.readyState === websocket.OPEN
}

/*
 * Update the textarea by appending the supplied text to the text that is
 * already there.  The text shows up as JSON, so it has to be parsed into
 * a JSON object to let us retrieve the data.
 */
function updateTextArea(data, inOut) {
    // STEP 4b: Convert string JSON into JSON
    // Parse the data as JSON so the fields can be accessed
    var json = JSON.parse(data);
    // Use the JSON notation to retrieve the data fields
    var name = json.name;
    var message = json.message;
    // Build the text to display then show it
    var out = (inOut == "in") ? "<div class=\"in\">" : "<div class=\"out\">";
    out += "<p>" + message + "</p><span>";
    out += (inOut == "in") ? name  : "Me";
    out += "</span></div>"
    var textArea = document.getElementById("messages");
    textArea.innerHTML = textArea.innerHTML + out;
    // Attempt to move the scrolling of the textarea to show the lowest item
    // The effectiveness of this varies by browser
    textArea.scrollTop = textArea.scrollHeight;
    // Logging only helps when you have the browser's developer tools open
    console.log("Writing: " + data);
}

/*
 * Clear any existing text from the message box and set focus there
 */
function prepMessageBox() {
    var messageBox = document.getElementById("message");
    messageBox.value = "";
    messageBox.focus();
}
//STEP 1: Add an event handler to listen for click, submission, etc.
const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function(event) {
    event.preventDefault(); // prevents http form requests
    // STEP 2: Get data from the user and validate it
    displayError(""); // Clear any messages
    // Get the user name
    var userName = document.getElementById("userName").value;
    if (userName === "") {
        // STEP 2b: Add span or div placeholders in HTML to display messages
        displayError("Name is required");
        return;
    }
    // Get the test of the message.  If the message is blank, use "..."
    var message = document.getElementById("message").value;
    if (message === "") {
        displayError("Message required");
        return;
    }
    // STEP 3:
    // Build a JSON object and convert it to a string so it can be sent
    var json = JSON.stringify({
        "name": userName,
        "message": message
    });
    // STEP 4: Update the current user's view
    // Update the textarea just like we would with an incoming message
    updateTextArea(json, "out");
    // STEP 5: Send the JSON to the Websocket Endpoint
    // Send the message
    sendText(json);
    // Set the message text field to blank so it is ready for the next message
    prepMessageBox();
});