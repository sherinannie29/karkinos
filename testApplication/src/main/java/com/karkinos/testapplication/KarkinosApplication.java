package com.karkinos.testapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"model.*"})
@EntityScan({"model.repo","model"})
@ImportResource("classpath*:applicationContext.xml")
@ComponentScan({"model.*","com.karkinos.*"})
@EnableAutoConfiguration
public class KarkinosApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarkinosApplication.class, args);
	}

}
