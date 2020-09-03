package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class MessageController {

    private SimpMessagingTemplate template;



    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public ResponseMessage getAndForward(MessageCommand command) {
        var content = command.getContent();
        log.debug("Message has arrived: {}", content);
        return new ResponseMessage(content);
    }

    @EventListener
    //@SendTo("/topic/employees")
    public void handleEmployeeHasCreatedEvent(
            EmployeeHasCreatedEvent event
    ) {
        template.convertAndSend("/topic/employees", new ResponseMessage(event.getMessage()));
    }
}
