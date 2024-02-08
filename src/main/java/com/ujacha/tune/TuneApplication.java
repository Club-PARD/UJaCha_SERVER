package com.ujacha.tune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class TuneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuneApplication.class, args);
	}

}
