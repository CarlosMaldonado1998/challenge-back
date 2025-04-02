package com.carlos.microservice1.core.services;

import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.carlos.microservice1.client.entities.CustomerEntity;
import com.carlos.microservice1.client.entities.PersonEntity;
import com.carlos.microservice1.client.repositories.ICustomerRepository;
import com.carlos.microservice1.client.repositories.IPersonRepository;
import com.carlos.microservice1.client.services.ICustomerService;
import com.carlos.microservice1.service.common.CustomBadRequestException;
import com.carlos.microservice1.vo.dto.CreateCustomerDto;
import com.carlos.microservice1.vo.dto.CustomerDto;
import com.carlos.microservice1.vo.dto.PersonDto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final IPersonRepository personRepository;

    private final PersonService personService;

    public CustomerService(ICustomerRepository customerRepository, IPersonRepository personRepository,
            PersonService personService) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @Transactional
    public CustomerEntity createCustomer(CreateCustomerDto createCustomerDto) {

        // Crear un objeto PersonDto a partir de los datos del DTO de cliente
        PersonDto newPerson = new PersonDto();
        newPerson.setAddress(createCustomerDto.getAddress());
        newPerson.setAge(createCustomerDto.getAge());
        newPerson.setGender(createCustomerDto.getGender());
        newPerson.setIdentification(createCustomerDto.getIdentification());
        newPerson.setName(createCustomerDto.getName());
        newPerson.setPhone(createCustomerDto.getPhone());

        // Crear la persona asociada
        PersonEntity person = personService.createPerson(newPerson);

        Optional<PersonEntity> existingPerson = personRepository.findById(person.getPersonId());
        if (!existingPerson.isPresent()) {
            throw new CustomBadRequestException("Persona no encontrada", HttpStatus.NOT_FOUND);
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPassword(createCustomerDto.getPassword());
        customerEntity.setPerson(existingPerson.get()); 

        // Guardar y devolver el cliente
        return customerRepository.save(customerEntity);
    }

    public Optional<CustomerEntity> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public CustomerEntity updateCustomer(Integer id, CustomerDto customerDTO) {
        Optional<CustomerEntity> existingCustomerOpt = customerRepository.findById(id);
        
        if (existingCustomerOpt.isPresent()) {
            CustomerEntity existingCustomer = existingCustomerOpt.get();
            existingCustomer.setPassword(customerDTO.getPassword());
            return customerRepository.save(existingCustomer);
        } else {
            throw new EntityNotFoundException("El cliente con ID " + id + " no se ha encontrado");
        }
    }

    public void deleteCustomer(Integer id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id + " no se ha encontrado"));
        customer.setStatus(false);
        customerRepository.save(customer);
    }

    public Optional<CustomerEntity> getCustomerByIdentification(String identification) {
        return customerRepository.findByPersonIdentification(identification);
    }
}