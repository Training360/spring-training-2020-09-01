package employees.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventStoreGateway {

    private RestTemplate restTemplate;

    private String url;

    public EventStoreGateway(RestTemplateBuilder builder,
                             @Value("${eventstore.url}") String url) {
        this.restTemplate = builder.build();
        this.url = url;
    }

    public void sendEvent(String message) {
        var event = new Event(message);
        restTemplate.postForObject(url, event, String.class);
    }
}
