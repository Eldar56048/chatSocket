var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    document.getElementById("user").value = document.getElementById("username").value;
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val(), 'user':$("#username").val()}));
    $("#name").value="";
}

function showMessage(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showFile(file){
    $("#greetings").append(file);
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() {connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
    $("#sendFileForm").on("submit", function (e) {
        e.preventDefault();
        $.ajax({
            url: "/upload",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                console.log(res);
            },
            error: function (err) {
                console.error(err);
            }
        });
    });
});