package com.nk.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringBootFirstAppApplication extends SpringBootServletInitializer {

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder appBldr) {
		return appBldr.sources(SpringBootFirstAppApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstAppApplication.class, args);
	}
	
	@RequestMapping(value="/")
	public String hello() {
		return "Hello World..";
	}

}
