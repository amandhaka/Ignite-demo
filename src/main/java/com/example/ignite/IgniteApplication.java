package com.example.ignite;

import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableIgniteRepositories(basePackages = "com.example.ignite.repository.ignite")
@EnableJpaRepositories(basePackages =  "com.example.ignite.repository.jpa")
@SpringBootApplication
public class IgniteApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteApplication.class, args);
	}

}
