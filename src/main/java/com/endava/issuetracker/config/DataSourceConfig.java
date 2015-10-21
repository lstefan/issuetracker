package com.endava.issuetracker.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

/**
 * 
 * @author Livia Stefan
 *
 */
@Configuration
@PropertySource("classpath:data-access.properties")
public class DataSourceConfig {

	@Bean
	public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName, 
								@Value("${jdbc.url}")String url, 
								@Value("${jdbc.username}")String username, 
								@Value("${jdbc.password}")String password) throws ScriptException, SQLException {
		
		DriverManagerDataSource newDs = new DriverManagerDataSource();
		newDs.setDriverClassName(driverClassName);
		newDs.setUrl(url);
		newDs.setUsername(username);
		newDs.setPassword(password);
		
		//databasePopulator().populate(newDs.getConnection());
		return newDs;
	}

	// internal helpers

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}
}
