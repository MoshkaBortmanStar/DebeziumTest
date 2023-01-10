package com.example.debeziumtest.service;

import com.example.debeziumtest.entity.Customer;
import com.example.debeziumtest.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope.Operation;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void replicateData(Map<String, Object> customerData, Operation operation) {
        final ObjectMapper mapper = new ObjectMapper();
        final Customer customer = mapper.convertValue(customerData, Customer.class);

        if (Operation.DELETE == operation) {
            customerRepository.deleteById(customer.getId());
        } else {
            customerRepository.save(customer);
        }
    }
}