package io.mohkeita.customer.service;

import io.mohkeita.amqp.RabbitMQMessageProducer;
import io.mohkeita.clients.fraud.FraudCheckResponse;
import io.mohkeita.clients.fraud.FraudClient;
import io.mohkeita.clients.notification.NotificationRequest;
import io.mohkeita.customer.dto.CustomerRegistrationRequest;
import io.mohkeita.customer.model.Customer;
import io.mohkeita.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster

        FraudCheckResponse fraudCheckResponse  = fraudClient.isFraudster(customer.getId());

       if (fraudCheckResponse.isFraudster()) {
           throw new IllegalStateException("fraudster");
       }

        NotificationRequest notificationRequest =
                new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Mohkeita...",
                        customer.getFirstName())
        );

       rabbitMQMessageProducer.publish(
               notificationRequest,
               "internal.exchange",
               "internal.notification.routing-key"
       );



    }
}
