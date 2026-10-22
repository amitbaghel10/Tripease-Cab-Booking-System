package com.example.tripease.Transformer;

import com.example.tripease.DTO.Request.CustomerRequest;
import com.example.tripease.DTO.Response.CustomerResponse;
import com.example.tripease.Model.Customer;

public class CustomerTransfomer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
//        Customer customer = new Customer();
//        customer.setName(customerRequest.getName());
//        customer.setAge(customerRequest.getAge());
//        customer.setEmailId(customerRequest.getEmailId());
//        customer.setGender(customerRequest.getGender());
//        return customer;
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .emailId(customerRequest.getEmailId())
                .gender(customerRequest.getGender())
                .build();
        return customer;
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
//        CustomerResponse customerResponse = new CustomerResponse();
//        customerResponse.setName(customer.getName());
//        customerResponse.setAge(customer.getAge());
//        customerResponse.setEmailId(customer.getEmailId());
//        return customerResponse;

        return CustomerResponse.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .build();

    }
}
