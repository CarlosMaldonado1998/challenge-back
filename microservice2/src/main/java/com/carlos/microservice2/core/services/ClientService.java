package com.carlos.microservice2.core.services;

import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.carlos.microservice2.client.entities.CustomerEntity;
import com.carlos.microservice2.service.common.CustomNotFoundException;

@Service
public class ClientService {

    private final RestTemplate restTemplate;

    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerEntity getCustomerIdByIdentification(String identification) {
        String url = "http://localhost:8081/clientes/by-identification/{id}";
        ResponseEntity<CustomerEntity> response = restTemplate.exchange(url, HttpMethod.GET, null, CustomerEntity.class,
                identification);

        CustomerEntity customer = response.getBody();
        if (customer != null && customer.getCustomerId() != null) {
            return customer;
        }
        return null;
    }

}
