package com.endava.issuetracker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author Livia Stefan
 *
 */
@Configuration
@ComponentScan("com.endava.issuetracker.service")
@Import( { PersistenceConfig.class } )
public class BusinessConfig {

}
