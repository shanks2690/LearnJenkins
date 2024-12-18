package com.shanks.learnjenkins;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CtrlTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testHelloEndpoint() {
        // Arrange
        String url = "http://localhost:" + port + "/api/hello";

        // Act
        String response = this.restTemplate.getForObject(url, String.class);

        // Assert
        assertThat(response).isEqualTo("Hello World!");
    }
}
