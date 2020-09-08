package employees.auditlog.service;

import employees.infra.events.EmployeeHasCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditLogService {

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        log.debug("AUDIT LOG: {}", event.getMessage());
    }

    @EventListener
    public void handleEvent(Object o) {
        log.debug("ALL EVENTS: " + o);
    }
}
