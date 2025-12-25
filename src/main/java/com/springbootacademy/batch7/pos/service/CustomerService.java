package com.springbootacademy.batch7.pos.service;

import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.request.CustomerUpdateDTO;

public interface CustomerService {
    // interface is the agreement. This is only interface. interface cannot have a body. interface is an abstract method means the method has not the body. interface is a class only has the method name. Then we can create as many classes as we want for implementation and implement them using the interface. So this needs the implementation classes. So this interface needs the implementation package also which it is impl.
//    String getCustomer(); // Only the method name is here.

    public String saveCustomer(CustomerDTO customerDTO); // The object from the CustomerDTO should be captured so in here that object is captured by the customerDTO named parameter which it's type is CustomerDTO.

//    void updateCustomer(CustomerUpdateDTO customerUpdateDTO);
    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);
}

// This interface must have a method's return type related to the method in
