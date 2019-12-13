package io.simpolor.feign.remote;

import com.github.tomakehurst.wiremock.WireMockServer;
import feign.Feign;
import feign.Response;
import feign.gson.GsonDecoder;
import io.simpolor.feign.domain.Student;
import io.simpolor.feign.remote.feign.*;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;


public class SenderServiceTest {

    private RequestLineClient requestLineClient;

    private WireMockServer wireMockServer;

    @Before
    public void setUp() throws Exception {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        setupStub();

        this.requestLineClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(RequestLineClient.class, "http://localhost:8089/api/reservations");

        stubFor(post(urlEqualTo("/timeout-test"))
                        .willReturn(
                                aResponse()
                                        .withFixedDelay(500)
                        )
        );

    }

    private void setupStub(){
        wireMockServer.stubFor(get(urlEqualTo("/api/reservations"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("mock-data.json")));
    }
    @Test
    public void testFindAll(){

        // given
        long seq = 1;
        StudentRequest request =
                StudentRequest.builder()
                        .name("kim0hee")
                        .grade(2)
                        .age(18)
                        .hobby(Arrays.asList("축구"))
                        .build();

        // when
        StudentResponse actual = requestLineClient.post(seq, request);


        // than
        Assert.assertEquals("Ok", actual.getResult());
        Assert.assertEquals("Success", actual.getMessage());
    }
}
