package employees;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class EmployeesGateway {

    private StompSession session;

    public void subscribe() {
        var client = new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient())));

        var stompClient = new WebSocketStompClient(client);
        // Jackson JSON (de)szerializálásra
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        var future = stompClient.connect("ws://localhost:8080/websocket-endpoint",
                new MessageStompSessionHandler());
        session = retrieveSession(future);
    }

    public StompSession retrieveSession(Future<StompSession> future) {
        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IllegalStateException("No session", e);
        }
    }

    public void send(String line) {
        session.send("/app/messages", new Message(line));
    }
}
