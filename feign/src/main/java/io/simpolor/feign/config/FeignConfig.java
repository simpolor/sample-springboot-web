package io.simpolor.feign.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * basePackages를 지정안할 경우 Interface를 Autowired할 수 없다.
 *
 * feign-okhttp : request를 만들기 위해서 내부적으로 Square OkHttp 클라이언트를 사용하기 위해 사용
 * feign-gson : JSON 처리기로서 Google Gson을 사용하기 위해 로드
 * feign-slf4j : request들을 로깅하기 위해서 라이브러리 사용
 */
@Configuration
@EnableFeignClients(
        defaultConfiguration = FeignConfig.class,
        basePackages = {
                "io.simpolor.feign.remote.client"
        })
@RequiredArgsConstructor
public class FeignConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }
}