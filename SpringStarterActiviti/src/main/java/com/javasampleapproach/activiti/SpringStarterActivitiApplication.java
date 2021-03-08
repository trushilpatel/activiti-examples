package com.javasampleapproach.activiti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javasampleapproach.activiti.service.MyService;

@SpringBootApplication
public class SpringStarterActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStarterActivitiApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final MyService myService) {

		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				myService.createPersons();
			}
		};

	}

}
