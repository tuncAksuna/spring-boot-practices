package com.interview.tuncode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TuncodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuncodeApplication.class, args);
		log.info("DEMO PROJECT is being successfully ran !!");
	}

}
