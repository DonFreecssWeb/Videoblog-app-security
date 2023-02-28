package com.example.videoblogappsecurityjorge;

import com.example.videoblogappsecurityjorge.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
public class VideoBlogAppSecurityJorgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoBlogAppSecurityJorgeApplication.class, args);
    }

}
