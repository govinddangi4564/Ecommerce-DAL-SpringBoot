package com.govind.Ecommerce_DAL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcommerceDalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceDalApplication.class, args);
	}

}
