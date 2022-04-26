package io.simpolor.jsp.config;

import com.google.common.base.CaseFormat;
import org.springframework.context.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"io.simpolor.jsp"})
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("");
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean
	public OncePerRequestFilter snakeCaseConverterFilter(){

		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

				final Map<String, String[]> paramters = new ConcurrentHashMap<>();

				for(String param : request.getParameterMap().keySet()){
					String camelCaseParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);

					paramters.put(camelCaseParam, request.getParameterValues(param));
					paramters.put(param, request.getParameterValues(param));
				}

				filterChain.doFilter(new HttpServletRequestWrapper(request){

					@Override
					public String getParameter(String name){
						return paramters.containsKey(name) ? paramters.get(name)[0] : null;
					}

					@Override
					public Enumeration<String> getParameterNames(){
						return Collections.enumeration(paramters.keySet());
					}

					@Override
					public String[] getParameterValues(String name){
						return paramters.get(name);
					}

					@Override
					public Map<String, String[]> getParameterMap(){
						return paramters;
					}

				}, response);
			}

		};
	}

}
