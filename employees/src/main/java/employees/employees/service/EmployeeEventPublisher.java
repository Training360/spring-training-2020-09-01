package employees.employees.service;

import employees.infra.events.EmployeeHasCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeEventPublisher {

    private ApplicationEventPublisher publisher;

    @Async
    public void createEvent(String message) {
        // Dobunk egy eventet
        publisher.publishEvent(new EmployeeHasCreatedEvent(message));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            log.error("Interrupterd", ie);
        }

        log.info("READY");

    }
}
