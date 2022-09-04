package com.openlab.openlabcustomerservice.services;

import com.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import com.openlab.openlabcustomerservice.dto.CustomerResponseDTO;
import com.openlab.openlabcustomerservice.entities.Customer;
import com.openlab.openlabcustomerservice.mapper.CustomerMapper;
import com.openlab.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
 @Transactional
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private  CustomerMapper customerMapper;

    public  CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper ){
        this.customerMapper = customerMapper;
        this.customerRepository= customerRepository;
    }
    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        /*Mapping Object to object */
        Customer customer =customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
//        customer.setId(UUID.randomUUID().toString());
        Customer savedCustomer =this.customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = this.customerMapper.customerToCustomerResponseDTO(savedCustomer);

        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {

        return customerMapper.customerToCustomerResponseDTO(customerRepository.findById(id).get());
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = customers.stream().map(customer -> customerMapper.customerToCustomerResponseDTO(customer)).collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
