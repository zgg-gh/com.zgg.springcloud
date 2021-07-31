package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeginOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeginOrderMain.class,args);
    }
}
