package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Controller
public class EventsController {

    @GetMapping("/api/events")
    public SseEmitter pushEvents() throws IOException {
        var emitter = new SseEmitter();
        emitter.send("Connected");
        var thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var i = 0; i < 10; i++) {
                    try {
                        emitter.send("Number " + i);
                        Thread.sleep(1000);
                    }
                    catch (Exception e) {
                        // interrupted
                    }
                }
            }
        });
        thread.start();

        return emitter;
    }
}
