var button1 = document.getElementById("button1");
var button2 = document.getElementById("button2");
var button3 = document.getElementById("button3");
var button4 = document.getElementById("button4");
var button5 = document.getElementById("button5");

button1.onclick = submitbutton1clicked;
button2.onclick = submitbutton2clicked;
button3.onclick = submitbutton3clicked;
button4.onclick = submitbutton4clicked;
button5.onclick = submitbutton5clicked;

var newSubmitfeedbackRequest = new XMLHttpRequest();
var webSocket;
var messages = document.getElementById("messages");

function submitbutton1clicked() {
    handleSubmit(1)
}

function submitbutton2clicked() {
    handleSubmit(2)
}

function submitbutton3clicked() {
    handleSubmit(3)
}

function submitbutton4clicked() {
    handleSubmit(4)
}

function submitbutton5clicked() {
    handleSubmit(5)
}

function handleSubmit(buttonnr) {
    var text = document.getElementById("topic" + buttonnr).value;
    var score = document.getElementById("topic"+ buttonnr + "score").value;
    var naam = document.getElementById("naam"+ buttonnr).value;
    var json = createJson(naam, text, score, buttonnr);
    send(json);
}

function createJson(naam, text, score, nr) {
    return "{ \"text\": \"" + text + "\", " +
        "\"naam\": \"" + naam + "\", " +
        "\"score\": \"" + score + "\", " +
        "\"nr\": \"" + nr + "\"}"
}

function openSocket(){
    webSocket = new WebSocket("ws://localhost:8080/sockets");

    webSocket.onopen = function(event){
        console.log("socket connection opened")
    };

    webSocket.onmessage = function(event){
        var parsed;
        try {
            parsed = JSON.parse(event.data);
            writeResponse(parsed.naam, parsed.nr, parsed.score, parsed.text);
        } catch (e) {

        }
    };

    webSocket.onclose = function(event){
        console.log("socket connection closed")
    };
}

function send(text) {
    webSocket.send(text);
}

function closeSocket(){
    webSocket.close();
}

function writeResponse(naam, nr, score, text){
    //console.log("nr: " + nr+ " score: "+score+" text: "+ text);
    var feedbacknaam =  "feedback" + nr;
    var feedbackdiv = document.getElementById(feedbacknaam);

    feedbackdiv.innerHTML += "<br/>" + naam + ": " + text + " score: " + score;
}

openSocket();