package com.stodop.demo;

import com.stodop.demo.model.Users;
import com.stodop.demo.repositories.ProjectRepository;
import com.stodop.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping
	public String hello(){
		return "hello everyone!";
	}

	@Override
	public void run(String... args) throws Exception {

		int strength = 10;

		System.out.println("runing on port 4000!");


	}
}
