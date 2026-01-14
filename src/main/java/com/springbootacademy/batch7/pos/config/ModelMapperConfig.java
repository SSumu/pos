package com.springbootacademy.batch7.pos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // This annotation is necessary if it is a configuration class.
public class ModelMapperConfig {
    @Bean // This class must be mentioned as a bean.
    public ModelMapper modelMapper(){ // Method with the class name is required. modelMapper is the method name.
        return new ModelMapper(); // Class type should be returned.
    }
}

// We do this way because this needs to be done in a structured way. Creating a separate file like this and put the code in here is the structured way.