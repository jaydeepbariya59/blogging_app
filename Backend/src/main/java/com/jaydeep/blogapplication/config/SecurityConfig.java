package com.jaydeep.blogapplication.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jaydeep.blogapplication.security.CustomUserDetailsService;
import com.jaydeep.blogapplication.security.JwtAuthenticationEntryPoint;
import com.jaydeep.blogapplication.security.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public static final String[] PUBLIC_URL = {
			"/auth/login",
			"/auth/registerUser",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/webjars/**",
			"/posts"
	};
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(PUBLIC_URL).permitAll()
			.antMatchers(HttpMethod.GET).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			
			.and()

			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    final CorsConfiguration config = new CorsConfiguration();
	    
	    config.setAllowCredentials(true);
	    config.setAllowedOriginPatterns(Collections.singletonList("*"));
	    config.setAllowedHeaders(Arrays.asList("Authentication", "Content-Type", "Accept"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    
	    source.registerCorsConfiguration("/**", config);
	    
	    FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	    
	    registration.setOrder(-110);
	    return registration;
	}

	
}
