package io.simpolor.feign.remote;

import com.github.tomakehurst.wiremock.WireMockServer;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.simpolor.feign.remote.feign.*;
import lombok.val;
import org.junit.*;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RequestLineClientTest {

    private RequestLineClient requestLineClient;

    private WireMockServer wireMockServer;

    @Before
    public void setUp() {
        this.requestLineClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(RequestLineClient.class, "http://localhost:8080");

        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        val givenResponse =
                "{\n" +
                        "  \"result\": \"OK\",\n" +
                        "  \"message\": \"Success\"\n" +
                        "}";

        wireMockServer.stubFor(post(urlEqualTo("/api/student/receiver/1"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(givenResponse)));

    }

    @Test
    public void testPost(){

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
        Assert.assertEquals("OK", actual.getResult());
        Assert.assertEquals("Success", actual.getMessage());


        // print
        System.out.println("actual : "+actual);
    }

}
