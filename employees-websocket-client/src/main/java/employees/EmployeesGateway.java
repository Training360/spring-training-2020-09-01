package employees;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;

@Service
public class EmployeesGateway {

    public void subscribe() {
        var client = new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient())));

        var stompClient = new WebSocketStompClient(client);
        // Jackson JSON (de)szerializálásra
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompClient.connect("ws://localhost:8080/websocket-endpoint",
                new MessageStompSessionHandler());
    }
}
