package io.simpolor.thymeleaf.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlComponent {

    @Value("${application.serverInfo.baseUrl}")
    private String baseUrl;

    public String getBaseUrl(){
        return baseUrl;
    }

    public String getFullUrl(String path) {
        return new StringBuilder()
                .append(baseUrl)
                .append(path)
                .toString();}

}
