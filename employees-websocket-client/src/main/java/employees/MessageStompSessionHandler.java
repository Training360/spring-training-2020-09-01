package employees;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.lang.reflect.Type;

public class MessageStompSessionHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        System.out.println("Connected...");
        stompSession.subscribe("/topic/employees", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        throw new IllegalStateException("BAJ!", throwable);
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        throw new IllegalStateException("NAGY A BAJ!", throwable);
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        var message = (Message) o;
        var content = message.getContent();
        System.out.println(content);
    }
}
