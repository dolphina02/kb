document.addEventListener('DOMContentLoaded', function() {
    var productId = /*[[${productId}]]*/ 0;
    var sessionId = '[[${sessionId}]]';

    console.log("Product ID: " + productId);
    console.log("Session ID: " + sessionId);

    var stompClient = null;

    function connect() {
        console.log("Connecting with session ID: " + sessionId); // 세션 ID 확인
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/chat/' + productId, function (message) {
                console.log('Received message: ', message.body); // 로그 추가
                showMessage(JSON.parse(message.body));
            });

            stompClient.send("/app/chat.addUser",
                {},
                JSON.stringify({sender: sessionId, type: 'JOIN'})
            );

            document.querySelector('#chatPage').classList.remove('d-none');
        }, function (error) {
            console.error('Connection error: ' + error);
            setTimeout(connect, 5000); // 5초 후에 재연결 시도
        });

        socket.onclose = function(event) {
            console.log('Socket closed: ' + event);
        };
    }

    function sendMessage(event) {
        var messageContent = document.querySelector('#message').value.trim();
        if (messageContent && stompClient) {
            var chatMessage = {
                sender: sessionId,
                content: messageContent,
                type: 'CHAT'
            };
            console.log('Sending message: ', chatMessage); // 로그 추가
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            document.querySelector('#message').value = '';

            // 메시지를 전송한 직후 화면에 표시
            showMessage(chatMessage);
        }
        event.preventDefault();
    }

    function showMessage(message) {
        console.log('Showing message: ', message); // 로그 추가
        var messageArea = document.querySelector('.message-area');
        var messageElement = document.createElement('div');
        messageElement.classList.add('chat-message');

        if (message.type === 'JOIN') {
            messageElement.classList.add('event-message');
            messageElement.textContent = message.sender + ' joined!';
        } else if (message.type === 'LEAVE') {
            messageElement.classList.add('event-message');
            messageElement.textContent = message.sender + ' left!';
        } else {
            var usernameElement = document.createElement('strong');
            usernameElement.classList.add('nickname');
            var usernameText = document.createTextNode(message.sender + ': ');
            usernameElement.appendChild(usernameText);

            var textElement = document.createElement('span');
            var messageText = document.createTextNode(message.content);
            textElement.appendChild(messageText);

            messageElement.appendChild(usernameElement);
            messageElement.appendChild(textElement);
        }

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }

    connect();

    var messageForm = document.querySelector('#messageForm');
    if (messageForm) {
        messageForm.addEventListener('submit', sendMessage, true);
    } else {
        console.error('Message form not found');
    }
});
