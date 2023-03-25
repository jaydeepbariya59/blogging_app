package com.jaydeep.blogapplication.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConstants {
	
	public static final String PAGE_NUMBER = "1";
	public static final String PAGE_SIZE = "1";
	public static final String SORT_BY = "title";
	public static final Integer ROLE_NORMAL_USER = 2;
	public static final Integer ROLE_ADMIN_USER = 1;
}
