const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/ws-stomp'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

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
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    //채팅할 방 구독
    const roomUrl = '/sub/chat/room/' + $("#roomId").val();
    stompClient.subscribe(roomUrl, (chatDto) => {
        console.log(JSON.parse(chatDto.body).message);
        showGreeting(JSON.parse(chatDto.body).message);
    });

    stompClient.publish({
        destination: "/pub/chat/enterUser",
        body: JSON.stringify({
            'type': 'ENTER',
            'roomId': $("#roomId").val(),
            'sender': $("#name").val(),
            'time': "오후 3:00"
        })
    });
}

function sendMsg() {
    stompClient.publish({
        destination: "/pub/chat/sendMessage",
        body: JSON.stringify({
            'type': 'TALK',
            'roomId': $("#roomId").val(),
            'sender': $("#name").val(),
            'message': $("#message").val(),
            'time': "오후 3:00"
        })
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#Entrance").click(() => sendName());
    $( "#send" ).click(() => sendMsg());
});