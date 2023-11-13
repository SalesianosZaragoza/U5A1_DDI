package com.in28minutes.springboot.jdbc.h2.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.springboot.jdbc.h2.example.repopsitory.StudentRepopsitory;
import com.in28minutes.springboot.jdbc.h2.example.student.Student;

@SpringBootApplication
public class Springboot2jdbcwithh2application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepopsitory repository;

	public static void main(String[] args) {
		SpringApplication.run(Springboot2jdbcwithh2application.class, args);
	}

	@Override
    public void run(String...args) throws Exception {

		logger.info("student id 10001 -> {}", repository.findbyid(10001l));

        logger.info("all users 1 -> {}", repository.findall());

		logger.info("inserting -> {}", repository.insert(new Student(10010l, "john", "a1234657")));

		logger.info("update 10001 -> {}", repository.update(new Student(10001l, "name-updated", "new-passport")));

        repository.deletebyid(10002l);

        logger.info("all users 2 -> {}", repository.findall());

    }
}