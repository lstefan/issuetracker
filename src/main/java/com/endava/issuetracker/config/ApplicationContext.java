package com.endava.issuetracker.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.endava.issuetracker.config.BusinessConfig;
import com.endava.issuetracker.config.SecurityConfig;
import com.endava.issuetracker.config.SocialConfig;
import com.endava.issuetracker.config.WebMvcConfig;

/**
 * @author Livia Stefan
 */
@Configuration
@ComponentScan("com.endava.issuetracker")
@Import( { BusinessConfig.class, WebMvcConfig.class, SecurityConfig.class, SocialConfig.class} )
@PropertySource("classpath:data-access.properties")
public class ApplicationContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

    // Message source for this context, loaded from localized "messages_xx" files.
    // Files are stored inside src/main/resources
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(new String[] { "classpath:/messages/messages" });
		return messageSource;
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public HiddenHttpMethodFilter httpMethodFilter() {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		filter.setMethodParam("issuetracker");
		return filter;
	}	
}
