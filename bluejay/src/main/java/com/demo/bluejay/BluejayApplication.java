package com.demo.bluejay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class BluejayApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BluejayApplication.class, args);
	}
	 @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder 
	   application) {
	      return application.sources(BluejayApplication.class);
	   }
	
}
