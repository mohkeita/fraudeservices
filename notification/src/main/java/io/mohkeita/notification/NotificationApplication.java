package io.mohkeita.notification;

import io.mohkeita.amqp.RabbitMQMessageProducer;
import io.mohkeita.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "io.mohkeita.notification",
                "io.mohkeita.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }


   /*
    @Bean
   CommandLineRunner commandLineRunner(RabbitMQMessageProducer messageProducer,
                                       NotificationConfig notificationConfig) {
        return args -> {
            messageProducer.publish(
                    new Person("Mohamed", 31),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());

        };
    }

    record Person(String name, int age) {

    }

    */
}


