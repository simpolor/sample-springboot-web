package io.simpolor.feign.config;

import com.netflix.hystrix.HystrixCommand;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.hystrix.HystrixFeign;
import feign.optionals.OptionalDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.*;
import org.springframework.cloud.openfeign.support.*;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * basePackages를 지정안할 경우 Interface를 Autowired할 수 없다.
 *
 * feign-okhttp : request를 만들기 위해서 내부적으로 Square OkHttp 클라이언트를 사용하기 위해 사용
 * feign-gson : JSON 처리기로서 Google Gson을 사용하기 위해 로드
 * feign-slf4j : request들을 로깅하기 위해서 라이브러리 사용
 */
@Slf4j
@Configuration
@EnableFeignClients(
        defaultConfiguration = FeignConfig.class,
        basePackages = {
                "io.simpolor.feign.remote.feign"
        })
public class FeignConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
                return new FormEncoder(new SpringEncoder(messageConverters));
        }

        /*
        @Autowired
        private Validator validator;

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        @Scope("prototype")
        public Feign.Builder validatableFeignBuilder() {

        return ExtendedFeign.builder(validator)
                .logLevel(Logger.Level.FULL);
        }

        @Bean
        public Encoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(messageConverters));
        }
        */

        /*
        @Autowired(required = false)
        private Logger logger;

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Autowired(required = false)
        private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

        @Autowired(required = false)
        private List<FeignFormatterRegistrar> feignFormatterRegistrars = new ArrayList<>();

        // ResponseEntity에 감싼다.
        @Bean
        @ConditionalOnMissingBean
        public Decoder feignDecoder() {
                return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters)));
        }

        @Bean
        @ConditionalOnMissingBean
        public Encoder feignEncoder() {
                return new SpringEncoder(this.messageConverters);
        }

        @Bean
        @ConditionalOnMissingBean
        public Contract feignContract(ConversionService feignConversionService) {
                return new SpringMvcContract(this.parameterProcessors, feignConversionService);
        }

        @Bean
        public FormattingConversionService feignConversionService() {
                FormattingConversionService conversionService = new DefaultFormattingConversionService();
                for (FeignFormatterRegistrar feignFormatterRegistrar : feignFormatterRegistrars) {
                    feignFormatterRegistrar.registerFormatters(conversionService);
                }
                return conversionService;
        }

        @Configuration
        @ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
        protected static class HystrixFeignConfiguration {
                @Bean
                @Scope("prototype")
                @ConditionalOnMissingBean
                @ConditionalOnProperty(name = "feign.hystrix.enabled")
                public Feign.Builder feignHystrixBuilder() {
                    return HystrixFeign.builder();
                }
        }

        // 재시도
        @Bean
        @ConditionalOnMissingBean
        public Retryer feignRetryer() {
                return Retryer.NEVER_RETRY;
        }

        @Bean
        @Scope("prototype")
        @ConditionalOnMissingBean
        public Feign.Builder feignBuilder(Retryer retryer) {
                return Feign.builder().retryer(retryer);
        }

        // 로그
        @Bean
        @ConditionalOnMissingBean(FeignLoggerFactory.class)
        public FeignLoggerFactory feignLoggerFactory() {
                return new DefaultFeignLoggerFactory(logger);
        }
        */
}

