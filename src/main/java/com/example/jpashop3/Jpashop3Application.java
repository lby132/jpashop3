package com.example.jpashop3;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jpashop3Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpashop3Application.class, args);
	}


	@Bean
	Hibernate5Module hibernate5Module() {
		final Hibernate5Module hibernate5Module = new Hibernate5Module();
	//	hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;
	}

}
