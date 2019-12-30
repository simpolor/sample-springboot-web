package io.simpolor.rest.remote;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class RestTemplateTest {

    @Test
    public void constructUriFromTemplate() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.simpolor.com")
                .path("/{article-name}")
                .query("q={query}")
                .buildAndExpand("junit4", "simpolor");

        Assert.assertEquals("/junit4?q=simpolor", uriComponents.toUriString());
    }
}
