package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		String[] activeProfiles = {"tenantservice"};
		String[] appArgs = new String[activeProfiles.length];

		for (int i = 0; i < activeProfiles.length; i++) {
			appArgs[i] = "--spring.profiles.active=" + activeProfiles[i];
		}

		SpringApplication.run(DemoApplication.class, appArgs);
	}
}
