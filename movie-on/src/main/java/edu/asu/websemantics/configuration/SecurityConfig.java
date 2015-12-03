package edu.asu.websemantics.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import edu.asu.websemantics.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginService loginService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/welcome/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')").and()
				.formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/welcome")
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/login?logout").and().exceptionHandling().accessDeniedPage("/403").and().csrf();
	}

}
