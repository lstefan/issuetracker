package com.endava.issuetracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.endava.issuetracker.repository.UserRepository;
import com.endava.issuetracker.service.impl.SocialUserDetailsServiceImpl;
import com.endava.issuetracker.service.impl.UserDetailsServiceImpl;

/**
 * 
 * @author Livia Stefan
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		// Spring Security ignores request to static resources such as CSS or JS
		// files.
		.ignoring().antMatchers("/resources/**");
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.inMemoryAuthentication().withUser("user").password
	 * ("user").roles("USER");
	 * auth.inMemoryAuthentication().withUser("admin").password
	 * ("admin").roles("ADMIN"); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable(); http.authorizeRequests()
		 * .antMatchers("/").authenticated()
		 * .antMatchers("/**").access("hasRole('ROLE_ADMIN')")
		 * .antMatchers("/user/**").access("hasRole('ROLE_USER')")
		 * .and().formLogin().permitAll() .and().logout().permitAll();
		 */
		http.csrf().disable();
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login/authenticate")
				.failureUrl("/login?error=bad_credentials")
				// Configures the logout function
				.and().logout()
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                    .authorizeRequests()
                        .antMatchers("/").authenticated()
                        //.antMatchers("/**").authenticated()
                        //Anyone can access the urls
                        .antMatchers(
                                "/auth/**",
                                "/login",
                                "/signup/**",
                                "/user/register/**"
                        ).permitAll()

                        //The rest of the our application is protected.
                        .antMatchers("/**").access("hasRole('USER')")
                        //.antMatchers("/**").hasRole("ADMIN")
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                .and()
                    .apply(new SpringSocialConfigurer());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(passwordEncoder());
	}

	/**
	 * This is used to hash the password of the user.
	 */
	  
	 @Bean public PasswordEncoder passwordEncoder() { return (PasswordEncoder)
	 new BCryptPasswordEncoder(10); }
	 
	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new SocialUserDetailsServiceImpl(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userRepository);
	}

}