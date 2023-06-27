package com.example.imu;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImuApplication {
//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }
    public static void main(String[] args) {
        SpringApplication.run(ImuApplication.class, args);
    }

}
