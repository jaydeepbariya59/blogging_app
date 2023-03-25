package com.jaydeep.blogapplication;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jaydeep.blogapplication.entity.Role;
import com.jaydeep.blogapplication.repository.RoleRepository;


@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Role> roles = List.of(
				new Role(1,"ROLE_ADMIN"),
				new Role(2, "ROLE_NORMAL")
				);
		
		roleRepository.saveAll(roles);
		
		System.out.println(roles.get(0)+"  "+ roles.get(1));
 	}

}
