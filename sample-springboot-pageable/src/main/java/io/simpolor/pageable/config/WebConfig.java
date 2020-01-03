package io.simpolor.pageable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// @Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        Sort defaultSort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable defaultPage = new PageRequest(0, 10, defaultSort);

        SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver = new SortHandlerMethodArgumentResolver();
        sortHandlerMethodArgumentResolver.setSortParameter("sort");
        sortHandlerMethodArgumentResolver.setFallbackSort(defaultSort);

        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
        pageableResolver.setMaxPageSize(100);
        pageableResolver.setOneIndexedParameters(false);
        pageableResolver.setPageParameterName("page");
        pageableResolver.setSizeParameterName("size");
        pageableResolver.setFallbackPageable(defaultPage);

        argumentResolvers.add(sortHandlerMethodArgumentResolver);
        argumentResolvers.add(pageableResolver);
    }
}
