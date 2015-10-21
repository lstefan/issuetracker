package com.endava.issuetracker.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * @author Livia Stefan
 *
 */
@Configuration
@ComponentScan("com.endava.issuetracker.repository")
@EnableJpaRepositories("com.endava.issuetracker.repository")
@Import(DataSourceConfig.class)
public class PersistenceConfig {
	
	@Value("${jpa.database}") 
	private String database;
	
    // JPA EntityManagerFactory
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    	entityManagerFactoryBean.setDataSource(dataSource);
    	
    	HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    	jpaVendorAdapter.setDatabase(Database.valueOf(database));
    	jpaVendorAdapter.setShowSql(true);
    	entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    	
    	// BOTH Persistence Unit and Packages to Scan are NOT compatible, persistenceUnit will win
    	entityManagerFactoryBean.setPersistenceUnitName("issuetracker");
    	entityManagerFactoryBean.setPackagesToScan("com.endava.issuetracker.domain");

    	entityManagerFactoryBean.setJpaProperties(additionalProperties());
    	return entityManagerFactoryBean;
    }
    
	   Properties additionalProperties() {
		      Properties properties = new Properties();
		      properties.setProperty("hibernate.hbm2ddl.auto", "update");
		      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		      return properties;
	   }
	   
	// Transaction manager for a single JPA EntityManagerFactory (alternative to JTA)
	@Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
    	return new JpaTransactionManager(entityManagerFactory(dataSource).getObject());
    }

    // Post-processor to perform exception translation on @Repository classes (from native
    // exceptions such as JPA PersistenceExceptions to Spring's DataAccessException hierarchy).
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    	return new PersistenceExceptionTranslationPostProcessor();
    }
    
	@Bean
	public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor(EntityManagerFactory emf) {
		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
		interceptor.setEntityManagerFactory(emf);
		return interceptor;
	}

}
