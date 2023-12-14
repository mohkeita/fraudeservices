package io.mohkeita.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "io.mohkeita.customer",
                "io.mohkeita.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "io.mohkeita.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {

        SpringApplication.run(CustomerApplication.class, args);
    }
}
