package com.example.demo;

import com.example.demo.common.SpringBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan("com.example.demo.user.mapper")
@ComponentScan("com.example.demo")
@EnableScheduling
@EnableAsync
public class DemoApplication {

	@Bean
	public SpringBeanUtils getSpringUtil() {
		return new SpringBeanUtils();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
