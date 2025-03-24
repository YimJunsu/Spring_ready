package com.study.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication
@MapperScan("com.study.spring.jdbc")
public class Exstudy2Application {

	public static void main(String[] args) {
		SpringApplication.run(Exstudy2Application.class, args);
	}

}
