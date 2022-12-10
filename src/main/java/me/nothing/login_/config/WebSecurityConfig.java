package me.nothing.login_.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import me.nothing.login_.filter.BeforeAuthenticationFilter;
import me.nothing.login_.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// @Autowired
	// private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	// @Autowired
	// private BeforeAuthenticationFilter beforeAuthenticationFilter;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers().authenticated()
			.anyRequest().permitAll()
			.and()
			//.addFilterBefore(beforeAuthenticationFilter, BeforeAuthenticationFilter.class)
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/home")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll();
	}

	 
	// @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// 	return super.authenticationManagerBean();
	// }

}
