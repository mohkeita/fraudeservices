package io.mohkeita.customer.service;

import io.mohkeita.customer.dto.CustomerRegistrationRequest;
import io.mohkeita.customer.model.Customer;
import io.mohkeita.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email taken
        customerRepository.save(customer);

    }
}
