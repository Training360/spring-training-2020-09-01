package employees;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public ResponseMessage getAndForward(MessageCommand command) {
        var content = command.getContent();
        return new ResponseMessage(content);
    }
}
