package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement
public class SpringbootTest1Application {

	@Bean
	@LoadBalanced//访问eurekaServer集群
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTest1Application.class, args);
	}
}
