package io.mohkeita.fraud.controller;

import io.mohkeita.clients.fraud.FraudCheckResponse;
import io.mohkeita.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping (path = "{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerID) {
       boolean isFraudulentCustomer = fraudCheckService
               .isFraudulentCustomer(customerID);

       log.info("fraud check request for customer {}", customerID);

       return new FraudCheckResponse(isFraudulentCustomer);
    }
}
