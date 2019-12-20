package io.simpolor.feign.remote;

import io.simpolor.feign.config.FeignConfig;
import io.simpolor.feign.domain.Student;
import io.simpolor.feign.domain.Type;
import io.simpolor.feign.remote.feign.*;
import io.simpolor.feign.web.response.ApiResponse;
import lombok.val;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.*;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FeignConfig.class, SenderService.class, SenderClient.class})
@ImportAutoConfiguration({
        HttpMessageConvertersAutoConfiguration.class,
        RibbonAutoConfiguration.class,
        FeignRibbonClientAutoConfiguration.class,
        FeignAutoConfiguration.class})
@AutoConfigureWireMock(port = 0)
public class SenderServiceTest {

    @Autowired
    private SenderService senderService;

    @Before
    public void setUp() {
        val givenResponse =
                "{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"message\": \"Success\"\n" +
                "}";

        stubFor(post(urlEqualTo("/api/student/receiver/1"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(givenResponse)));
    }

    @Test
    public void testSend(){

        // given
        Student student =
                Student.builder()
                        .seq(1)
                        .name("kim0hee")
                        .grade(2)
                        .age(18)
                        .hobby(Arrays.asList("축구"))
                        .build();

        // when
        ApiResponse actual = senderService.send(Type.REST, student);


        // than
        Assert.assertEquals("000", actual.getResultCode());
        Assert.assertEquals("OK", actual.getResultMessage());
    }
}
