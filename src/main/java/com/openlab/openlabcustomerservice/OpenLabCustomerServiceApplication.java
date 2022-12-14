package com.openlab.openlabcustomerservice;

import com.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import com.openlab.openlabcustomerservice.services.CustomerService;
import org.mapstruct.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenLabCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenLabCustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return  args -> {
            customerService.save(new CustomerRequestDTO("C01","ismail" , "ismail@123"));
            customerService.save(new CustomerRequestDTO("C02","zaid" , "ismail@123"));
            customerService.save(new CustomerRequestDTO("C03","reda" , "ismail@123"));
            customerService.save(new CustomerRequestDTO("C04","saad" , "ismail@123"));
            customerService.save(new CustomerRequestDTO("C05","yassir" , "ismail@123"));
        };
    }
}

