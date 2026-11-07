package pl.piomin.java.samples.smallapi;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTests {

    private HttpServer server;
    private URI uri;

    @BeforeEach
    void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/hello", exchange -> {
            byte[] body = ("hello " + exchange.getRequestMethod() + " "
                    + exchange.getRequestHeaders().getFirst("X-Test")).getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            try (var os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.start();
        uri = URI.create("http://" + InetAddress.getLoopbackAddress().getHostAddress()
                + ":" + server.getAddress().getPort() + "/hello");
    }

    @AfterEach
    void stop() {
        server.stop(0);
    }

    @Test
    void fluentRequest() throws Exception {
        try (HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // falls back to HTTP/1.1 for plain http
                .connectTimeout(Duration.ofSeconds(5))
                .build()) {
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("X-Test", "abc")
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            assertEquals("hello GET abc", response.body());
        }
    }

    @Test
    void async() throws Exception {
        try (HttpClient client = HttpClient.newHttpClient()) {
            String body = client.sendAsync(
                            HttpRequest.newBuilder(uri).header("X-Test", "z").build(),
                            HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .get();
            assertEquals("hello GET z", body);
        }
    }
}
