package org.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean //@Bean相当于applicationContext.xml文件中的bean标签
   // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

