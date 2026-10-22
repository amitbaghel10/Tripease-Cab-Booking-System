package com.example.tripease.Service;

import com.example.tripease.DTO.Request.CustomerRequest;
import com.example.tripease.DTO.Response.CustomerResponse;
import com.example.tripease.Enum.Gender;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Model.Customer;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Transformer.CustomerTransfomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //RequestDTO -> Entity
        Customer customer = CustomerTransfomer.customerRequestToCustomer(customerRequest);

        //save the entity to DB
        Customer savedCustomer = customerRepository.save(customer);

        //save entity -> Response  DTO
        return CustomerTransfomer.customerToCustomerResponse(customer);

    }

    public CustomerResponse getCustomer(int customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        Customer savedCustomer = optionalCustomer.get();

        //save entity -> Response  DTO
        return CustomerTransfomer.customerToCustomerResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllByGender(Gender gender){

        List<Customer> customers = customerRepository.findByGender(gender);

        //entity -> Response DTO
        List<CustomerResponse> customerReponses = new ArrayList<>();

        for(Customer customer :customers){
            customerReponses.add(CustomerTransfomer.customerToCustomerResponse(customer));
        }
        return customerReponses;


    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {

        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);

        //entity -> ResponseDTO
        List<CustomerResponse> customerResponse = new ArrayList<>();

        for(Customer customer : customers){
            customerResponse.add(CustomerTransfomer.customerToCustomerResponse(customer));
        }
        return customerResponse;
    }

    public CustomerResponse getByEmailId(String emailId) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailId(emailId);

        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Invalid email id");
        }

        Customer customer = optionalCustomer.get();

        return CustomerTransfomer.customerToCustomerResponse(customer);

    }

    /*public List<CustomerResponse> getAllByGenderAndAgeGreater(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAgeGreater(gender, age);

        //entity -> ResponseDTO
        List<CustomerResponse> customerResponse = new ArrayList<>();

        for(Customer customer : customers){
            customerResponse.add(CustomerTransfomer.customerToCustomerResponse(customer));
        }
        return customerResponse;
    }*/


    //by SQL Method
    public List<CustomerResponse> getAllByGenderAndAgeGreater(String gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAgeGreater(gender, age);

        //entity -> ResponseDTO
        List<CustomerResponse> customerResponse = new ArrayList<>();

        for(Customer customer : customers){
            customerResponse.add(CustomerTransfomer.customerToCustomerResponse(customer));
        }
        return customerResponse;
    }


}
