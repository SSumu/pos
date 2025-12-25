package com.springbootacademy.batch7.pos.service.impl;

//import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.batch7.pos.entity.Customer;
import com.springbootacademy.batch7.pos.repo.CustomerRepo;
import com.springbootacademy.batch7.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // There it uses this annotation to define this as a Service class. If we need objects from a class, we have to put @Component and there it wants to put it in the container as a bean which simply means an object. @Service annotation has the @Component annotation. So it already becomes a bean by this @Service and puts it into the container.
@Transactional // This keeps the Hibernate session open for the whole method. This is not mentioned in the video.
public class CustomerServiceIMPL implements CustomerService {
//    @Override
//    public String saveCustomerrr(CustomerDTO customerDTO) {
////        return "";
//        return null;
//    }

// When the CustomerService requires an object and the CustomerService implemented by the CustomerServiceIMPL class, CustomerServiceIMPL class gives an object in the Spring. We deal with the CustomerServiceIMPL class. We access the saveCustomer() through CustomerServiceIMPL class. When we require an object from CustomerService interface, CustomerServiceIMPL class gives an object as the interface cannot create objects.

// It says to implement the method in the CustomerService when the method name is changed.

@Autowired
private CustomerRepo customerRepo; // Added the object of CustomerRepo class to pass the data to the CustomerRepo class.

    @Override
    public String saveCustomer(CustomerDTO customerDTO) { // Data was correctly passed to the customerDTO. So the data has come to here must be sent the database.
//        return "";
//        return null;
//        System.out.println(customerDTO.getCustomerAddress());
//        Customer customer = customerDTO; // Convert the customerDTO to an entity. It means that it wants an object from Customer type. Object of customer must send to the save() to save.
//        Customer customer = new Customer(
////                1,
////                "sadeepal" // This error means this is searching for a constructor. There is no matching constructor to this. Because the constructor in the Customer class is a NoArgs constructor means no arguments in it and there is a AllArgs constructor with all the  arguments. I can get rid of this error if I create a constructor that matches this. The error was fixed by the constructor was created based on the Id and Name. Now we do not need any other evidence, let's just call the constructor that has only the Id and Name of the Customer class. No need to enter data manually to the customer object because there are some values that came from customerDTO. If I catch the reference of the customerDTO. There is a AllArgs constructor. All the arguments and values are in. It is correct if we call the AllArgs Customer() constructor in the Customer class for the values.
//        ); // Created the object of customer. It requires an entity. Even if it's null, it's still going. But the data that came from the frontend is in customerDTO. customer has not that data so we have to put that data into the customer. Many objects like this are created in any method. customer is not an object. Customer() Constructor is called from here. Constructor means an object has no return type, and it is created from the name of the class. Constructor is called at the time when the object is initialized.

        // This is the correct way of accessing the data in the dto which is using constructor. This is the correct idea.
        Customer customer = new Customer( // customer is an entity object.
                // This is called encapsulation. Encapsulation is protecting the data which we cannot access the data directly. There are Getters and Setters to access the data. In encapsulation, data can only be accessed through Getters and Setters and direct access is not given.

                customerDTO.getCustomerId(), // It is correct if we put the customerId sent from the frontend by the customerDTO.
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumber(),
//                customerDTO.isActive(), // Changed the sequence to show the error.
                customerDTO.getNic(),
                customerDTO.isActive()
                // We can remove the isActive method from Customer class if it does not allow us to access in another place. Then we cannot access the active status and it is called protecting the data.
                // There the error is disappeared because the data of this was set to match the type of the constructor as it can reach or fulfill its constructor. We cannot change the sequence of the Getters of customerDTO. If we change the sequence, then there is an error. Because this matches with the data in the Customer class.

                // This is the way how data in the DTO enters to the customer entity.

                // We can do this also in this way and it is not wrong. In that way, it does not just put from the Customer class to inside the CustomerServiceIMPL right away.
        );
//        Customer customer = new Customer(); // customer object
//        customer.setCustomerId(customerDTO.getCustomerId()); // customer is the reference. So the value is an int. We can give the id manually or from DTO. So we can get the customerId from the customerDTO. We can give all other methods like this and it is not wrong. In this way, we do not constructors. But this way is not recommended by the Lakshan Malinga sir.
//       There are get set methods in the entity also.
        // Then the customer object was passed inside the customerRepo's save method with the values sent from the frontend.
        customerRepo.save(customer); // save() requires an entity type. It shows an error if we put customerDTO as it is a dto. If the object of customer is sent to the save() method, then it is ok. Then the error is disappeared.
        return customerDTO.getCustomerName();
    }

    @Override
//    public void updateCustomer(CustomerUpdateDTO customerUpdateDTO) { // Implement the updateCustomer() was created in the CustomerService interface.
//
//    }
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) { // Implement the updateCustomer() was created in the CustomerService interface. Before update the customer's data, we must check that there is a customer with that id.
//        First we must check whether there is a customer with that id. So first I check my database that there is a customer from the Id was sent from the data from the frontend. It simply tells us to update the Name, Address and Salary related to that Id.
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())){ // existsById() is a boolean method and it requires an integer id. When the customerUpdateDTO is passed in to existsById(), the Id sent from the frontend is checked by the customerRepo or database to see if there is someone matching that Id.
//       Here, if there is a customer, when we use JPAQuerySpec we first need to fetch the customer from the database. Otherwise, if we write a direct query, then we have to give a direct query. If tables in a database are created from entities, then they also come from the database through entities. It can also put it in the database using entities. So I must bring the customer it has with the same type. I must be able to bring it from the Customer type. Otherwise, we cannot assign to this. We cannot assign a String to this. So the incoming data to here must be in Customer type. So I do not need to write queries for this. When I call the customerRepo, it shows me the method matches with the Customer type in the top. I do not need to search for it. If I want to assign something to this, the return type must be Customer. Return types of the methods are shown on the right side in the suggestion box. getById() and getOne() methods are deprecated so those methods had been in the previous versions and cannot be used now. So instead of those methods now it has the getReferenceById(). So I just only have to pass the Id to the getReferenceById(). When I pass the Id, getReferenceById() brings me the whole customer. I do not need to write queries. So I need to give the customerId of customerUpdateDTO when it has been sent from the frontend. When we give like this, it searches the database and find the whole customer object related to the Id which means we only send the customerId to the database. Then all the data related to the customer's ID, such as Name, Address, Salary, ContactNumber, nic and activeState are taken and assigned to the customer. Now we have to update it.
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId()); // This is the code related to the search operation. We searched the particular customer using this method.
//            Customer customer = customerRepo.findById(customerUpdateDTO.getCustomerId()); // This findById method is gotten from the ChatGPT.

            // We searched here and retrieved an object and updated the object.
            customer.setCustomerName(customerUpdateDTO.getCustomerName()); // customer is the reference(object). Name has been come from the frontend must be applied inside the setCustomerName(). Now set the update details that I sent from the frontend to the object retrieved from the database.
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress()); // Set the address that came from the frontend.
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary()); // Set the salary that came from the frontend.
            // So now in the customer object has not the values from the database. Now the customer object has the new values. Now if this object is sent, it's okay. Because in addition to the values that came from the database, I changed the values in the customer object.

            customerRepo.save(customer); // There is no update method. We use save() method for both save and update. When the customer is sent inside the save(), it first checks to see if there is customer in the database with the Id it is sending. If a customer exists, update the existing customer's changing details. Otherwise, save as a new customer.
            return customerUpdateDTO.getCustomerName() + " Updated Successful"; // Take the value of customerUpdateDTO.
            // The updateCustomer call goes from CustomerServiceIMPL to CustomerService. After that, it reverts back to CustomerServiceIMPL. Accordingly, the updateCustomer call is made from CustomerController. Accordingly, the value returned by CustomerServiceIMPL is returned to updateCustomer in CustomerController.
        }else {
            // If there is no such customer, I throw an exception that Java has ClassNotFoundException().
            throw new RuntimeException("no data found for that id");
        }
//        return null;
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)){ // Checking by Id whether there is a customer related to the id.
            // Even there the getCustomerById() is CustomerDTO type, you cannot bring DTOs from the database.
            Customer customer = customerRepo.getReferenceById(customerId); // The full details of the customer associated with the Id will be brought here.
            CustomerDTO customerDTO = new CustomerDTO( // customerDTO is the customer object.
                    customer.getCustomerId(), // But the reference is customer. customer has the data.
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            ); // Conversion of customer named CustomerEntity into a CustomerDTO
//            return customer; // If we return customer, it is a error. Because it must return a CustomerDTO and not a Customer. So we have to convert the customer named CustomerEntity to the CustomerDTO. So returning customer is wrong.
            return customerDTO; // So customerDTO must be returned.
//            The values that come from the customer, most of them come from the customer related to this ID. I am pushing on the customerDTO. I am grabbing it from the customer reference. The ID that came from the reference. The name that came from the reference. I am mapping the constructor like that. After that, return. Otherwise, I am returning an exception saying that there is no customer. After returning, the customerDTO, that is, the customer reference data, is inside the customerDTO.
        }else {
            throw new RuntimeException("No Customer");
        }
//        return null; // It is not needed to put a return in here.
    }
//    @Override
//    public String saveCustomer(CustomerDTO customerDTO) {
//        return "";  // But In my code, it gives "" as the return value.
//        return null;  // In the video, it gives null as the return value.
//    }

//    @Override
//    public String loveCustomer() {
//        // We just only have to change the body or to write only the implementation.
//        return "";
//    } // CustomerService interface is implemented by the CustomerServiceIMPL class. So the CustomerServiceIMPL class has been implemented by the CustomerService interface. Implementing the CustomerService interface means that only its method name must be included in the interface. Implementing the CustomerService interface means that the implementation corresponding to its method name must be written in the CustomerServiceIMPL class.

//    @Override
//    public String getCustomer() {
//        return "";
//    }
    // If the interface is not present, then the CustomerController should be sent to the CustomerServiceIMPL. The interface agreement is to stop the CustomerController and CustomerServiceIMPL classes from being tightly coupled. If the CustomerServiceIMPL class is implemented as required by the CustomerController class, that is a problem. But the CustomerServiceIMPL class cannot cause a problem for the CustomerController class. If the names of the methods in the CustomerServiceIMPL class related to the CustomerService interface are changed, those methods must be reimplemented.

//    This class must have an object of CustomerRepo to pass the data from this class to CustomerRepo.
}

// Only the beans are put in the container. We do not put POJOs in the container. We only define what the POJOs are.