package com.example.imu;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App REST APIs",
                description = "Spring Boot Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Alexandr",
                        email = "alexanderparpulansky@gmail.com",
                        url = "https://github.com/aparpEdu?tab=repositories"
                ),
                license = @License(
                        name = "Apache 2.0"
//                        url =
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Blog App Documentation",
                url = "https://github.com/aparpEdu/blogApp"
        )
)
public class ImuApplication {
//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }
    public static void main(String[] args) {
        SpringApplication.run(ImuApplication.class, args);
    }

}
