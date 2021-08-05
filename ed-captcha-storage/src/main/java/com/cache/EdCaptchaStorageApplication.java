package com.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdCaptchaStorageApplication {

	private static final Logger log = LoggerFactory.getLogger(EdCaptchaStorageApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(EdCaptchaStorageApplication.class, args);
		
		log.info("EdCaptchaStorageApplication started successfully.");
	}

}
