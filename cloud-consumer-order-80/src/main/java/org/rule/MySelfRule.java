package org.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MySelfRule {

    @Bean //@Bean相当于applicationContext.xml文件中的bean标签
    public IRule MyRule(){
        return new RandomRule();
    }
}
