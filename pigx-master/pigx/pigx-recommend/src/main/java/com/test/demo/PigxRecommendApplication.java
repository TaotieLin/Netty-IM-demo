package com.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan(basePackages = "com.test.demo.dao")
@EnableScheduling
@SpringBootApplication
public class PigxRecommendApplication {


	public static void main(String[] args) {
		SpringApplication.run(PigxRecommendApplication.class, args);
	}
}
