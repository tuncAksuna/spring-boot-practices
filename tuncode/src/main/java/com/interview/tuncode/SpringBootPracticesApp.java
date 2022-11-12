package com.interview.tuncode;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
})
@Slf4j
@EnableEncryptableProperties
public class SpringBootPracticesApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticesApp.class, args);
		log.info("DEMO PROJECT is being successfully ran !!");
	}

}
