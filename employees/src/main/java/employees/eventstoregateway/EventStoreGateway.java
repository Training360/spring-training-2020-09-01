package employees.eventstoregateway;

import employees.infra.gateway.Gateway;
import employees.infra.events.EmployeeHasCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@Gateway
public class EventStoreGateway {

    private RestTemplate restTemplate;

    private String url;

    public EventStoreGateway(RestTemplateBuilder builder,
                             @Value("${eventstore.url}") String url) {
        this.restTemplate = builder.build();
        this.url = url;
    }

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        sendEvent("Employee has been created: " + event.getMessage());
    }

    public void sendEvent(String message) {
        var event = new Event(message);
        restTemplate.postForObject(url, event, String.class);
    }
}
