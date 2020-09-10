package employees;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import employees.eventstoregateway.EventStoreGateway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.SocketUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class EventStoreGatewayIT {

    static String host = "127.0.0.1";

    static int port;

    static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        port = SocketUtils.findAvailableTcpPort();
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
        WireMock.configureFor(host, port);
        wireMockServer.start();
    }
    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @Test
    void testSendEvent() {
        String resource = "/api/events";

        stubFor(post(urlEqualTo(resource))
                .willReturn(ok()));

//        stubFor(post(urlEqualTo(resource))
//                .willReturn(notFound()));


        String url = String.format("http://%s:%d%s", host, port, resource);
        System.out.println(url);

        EventStoreGateway gateway =
                new EventStoreGateway(new RestTemplateBuilder(), url);

        gateway.sendEvent("Employee has been created with name John Doe");

        verify(postRequestedFor(urlPathEqualTo(resource))
                .withRequestBody(
                        equalToJson("{\"message\": \"Employee has been created with name John Doe\"}")));
    }

    @BeforeEach
    void resetServer() {
        WireMock.reset();
    }
}
