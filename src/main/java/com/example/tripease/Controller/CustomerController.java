package com.example.tripease.Controller;

import com.example.tripease.DTO.Request.CustomerRequest;
import com.example.tripease.DTO.Response.CustomerResponse;
import com.example.tripease.Enum.Gender;
import com.example.tripease.Model.Customer;
import com.example.tripease.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int customerId){
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/get/customer-gender/{gender}")
    public List<CustomerResponse> getAllByGender(@PathVariable("gender")Gender gender){
        return customerService.getAllByGender(gender);

    }

    @GetMapping("/get-by-emailId")
    public CustomerResponse getByEmailId(@RequestParam("email") String emailId){
        return customerService.getByEmailId(emailId);
    }

    // Get all the customer of a particular gender and age
    // Ex- All Males of age 25 and all females of age 24
    @GetMapping("/get-by-gender-age")
    public List<CustomerResponse> getAllByGenderAndAge(@RequestParam("gender") Gender gender,
                                                       @RequestParam("age") int age){
        return customerService.getAllByGenderAndAge(gender,age);
    }

    // Get all the customer of a paricular gender and whose age > input age
    /*@GetMapping("/get-by-age-greater")
    public List<CustomerResponse> getAllByGenderAndAgeGreater (@RequestParam("gender") Gender gender,
                                                            @RequestParam("age") int age) {
        return customerService.getAllByGenderAndAgeGreater(gender, age);
    }*/



    //by SQL Query
    @GetMapping("/get-by-age-greater")
    public List<CustomerResponse> getAllByGenderAndAgeGreater (@RequestParam("gender") String gender,
                                                            @RequestParam("age") int age) {
        return customerService.getAllByGenderAndAgeGreater(gender, age);
    }
}
