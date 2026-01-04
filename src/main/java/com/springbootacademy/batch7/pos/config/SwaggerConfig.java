//package com.springbootacademy.batch7.pos.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration // This annotation is to define this as a configuration class.
//public class SwaggerConfig {
//    // The class of this is in the Docket type. If we give a return in that type, then it directly puts into the container.
//
//    @Bean // We must give this annotation to say that this is a bean. If you say it's a bean, it goes and puts in the container.
//    public Docket SwaggerApi(){ // We can give any name to this method. So this is the method syntax.
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build(); // So it tells us that we must give the DocumentationType.SWAGGER_2. It is not incorrect to give the select() in here.
////        That is all you need to configure Swagger.
//    }
//}

// This is an old way and does not use in the Spring 6.
// Even the code in this file is commented, it is not a good practice.
// DELETE SwaggerConfig.java completely.
// ❌ Why delete it?
// This is 100% Springfox-specific
// Docket, DocumentationType, PathSelectors are not used anymore.
// Springdoc does NOT require a config class.
// 🗑️ Delete the file (do not comment it).