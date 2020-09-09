package employees.messages.controller;

import employees.infra.events.EmployeeHasCreatedEvent;
import employees.messages.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class EventsController {

    private List<SseEmitter> emitters =
            Collections.synchronizedList(new ArrayList<>());

    @GetMapping("/api/events")
    public SseEmitter pushEvents() throws IOException {
        var emitter = new SseEmitter();
        emitter.send("Connected");
        emitters.add(emitter);
//        var thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (var i = 0; i < 10; i++) {
//                    try {
//                        emitter.send("Number " + i);
//                        Thread.sleep(1000);
//                    }
//                    catch (Exception e) {
//                        // interrupted
//                    }
//                }
//            }
//        });
//        thread.start();

        return emitter;
    }

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        for (var emitter: emitters) {
            try {
                //emitter.send(event.getMessage());
                SseEmitter.SseEventBuilder builder = SseEmitter.event()
                        .name("message")
                        .comment("Employee has created")
                        .id(UUID.randomUUID().toString())
                        .reconnectTime(10_000)
                        .data(new ResponseMessage("Employee has created: " + event.getMessage()));
                emitter.send(builder);
            }
            catch (Exception ioe) {
                log.debug("Emitter has died");
            }
        }
    }
}
