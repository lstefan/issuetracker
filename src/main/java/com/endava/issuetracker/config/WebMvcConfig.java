package com.endava.issuetracker.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 
 * @author Livia Stefan
 *
 */
@Configuration
@ComponentScan("com.endava")
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {


	@Bean
	public ViewResolver beanNameViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
	 	return viewResolver;
	}
   

	
/*    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("login");	
    	registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }*/



	// This bean resolves specific types of exceptions to corresponding logical 
    // view names for error views.
	@Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    	SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
    	resolver.setDefaultErrorView("exception");
    	resolver.setWarnLogCategory("warn");
    	return resolver;
    }
	   
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/resources/**", "/webjars/**")
		.addResourceLocations("/resources/", "classpath:/META-INF/resources/webjars/")
		.setCachePeriod(31556926);
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true);
		configurer.ignoreAcceptHeader(true);
		configurer.defaultContentType(MediaType.TEXT_HTML);
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("html", MediaType.TEXT_HTML);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("atom", MediaType.APPLICATION_ATOM_XML);
		configurer.mediaTypes(mediaTypes);
	}
	
}
