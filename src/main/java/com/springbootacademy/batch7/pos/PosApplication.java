package com.springbootacademy.batch7.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;  // This is not used in the Spring 6 version.

@SpringBootApplication
//@EnableSwagger2 // To use Swagger we have to give this annotation. Swagger configuration. This annotation is used in the old Spring 4 / 5 versions. But this annotation is not used in Spring 6 version. It is removed. Springdoc is used instead of Swagger.
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}

}

// This is the main class (PosApplication). This is the entry point. com.springbootacademy.batch7.pos is the main package. Other packages are created inside of this main package.

// entity package can be named either entity or model.
