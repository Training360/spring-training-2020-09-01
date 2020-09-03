package employees;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;

@Service
public class EmployeesGateway {

    public void subscribe() {
        var client = new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient())));
    }
}
