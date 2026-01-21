package com.springbootacademy.batch7.pos.service.impl;

//import com.springbootacademy.batch7.pos.dto.CustomerDTO;

import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.batch7.pos.entity.Customer;
import com.springbootacademy.batch7.pos.exception.NotFoundException;
import com.springbootacademy.batch7.pos.repo.CustomerRepo;
import com.springbootacademy.batch7.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// There it uses this annotation to define this as a Service class. If we need objects from a class, we have to put @Component and there it wants to put it in the container as a bean which simply means an object. @Service annotation has the @Component annotation. So it already becomes a bean by this @Service and puts it into the container.
@Transactional // This keeps the Hibernate session open for the whole method. This is not mentioned in the video.
public class CustomerServiceIMPL implements CustomerService {
//    @Override
//    public String saveCustomerrr(CustomerDTO customerDTO) {
    /// /        return "";
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
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) { // existsById() is a boolean method and it requires an integer id. When the customerUpdateDTO is passed in to existsById(), the Id sent from the frontend is checked by the customerRepo or database to see if there is someone matching that Id.
//       Here, if there is a customer, when we use JPAQuerySpec we first need to fetch the customer from the database. Otherwise, if we write a direct query, then we have to give a direct query. If tables in a database are created from entities, then they also come from the database through entities. It can also put it in the database using entities. So I must bring the customer it has with the same type. I must be able to bring it from the Customer type. Otherwise, we cannot assign to this. We cannot assign a String to this. So the incoming data to here must be in Customer type. So I do not need to write queries for this. When I call the customerRepo, it shows me the method matches with the Customer type in the top. I do not need to search for it. If I want to assign something to this, the return type must be Customer. Return types of the methods are shown on the right side in the suggestion box. getById() and getOne() methods are deprecated so those methods had been in the previous versions and cannot be used now. So instead of those methods now it has the getReferenceById(). So I just only have to pass the Id to the getReferenceById(). When I pass the Id, getReferenceById() brings me the whole customer. I do not need to write queries. So I need to give the customerId of customerUpdateDTO when it has been sent from the frontend. When we give like this, it searches the database and find the whole customer object related to the Id which means we only send the customerId to the database. Then all the data related to the customer's ID, such as Name, Address, Salary, ContactNumber, nic and activeState are taken and assigned to the customer. Now we have to update it.
//            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId()); // This is the code related to the search operation. We searched the particular customer using this method.
//            Customer customer = customerRepo.findById(customerUpdateDTO.getCustomerId()); // This findById method is gotten from the ChatGPT.

            Customer customer = customerRepo.getById(customerUpdateDTO.getCustomerId()); // This codeline was not needed for this code but in the video it includes this code line for its code error.

            // We searched here and retrieved an object and updated the object.
            customer.setCustomerName(customerUpdateDTO.getCustomerName()); // customer is the reference(object). Name has been come from the frontend must be applied inside the setCustomerName(). Now set the update details that I sent from the frontend to the object retrieved from the database.
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress()); // Set the address that came from the frontend.
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary()); // Set the salary that came from the frontend.
            // So now in the customer object has not the values from the database. Now the customer object has the new values. Now if this object is sent, it's okay. Because in addition to the values that came from the database, I changed the values in the customer object.

            customerRepo.save(customer); // There is no update method. We use save() method for both save and update. When the customer is sent inside the save(), it first checks to see if there is customer in the database with the ID it is sending. If a customer exists, update the existing customer's changing details. Otherwise, save as a new customer.
            return customerUpdateDTO.getCustomerName() + " Updated Successful"; // Take the value of customerUpdateDTO.
            // The updateCustomer call goes from CustomerServiceIMPL to CustomerService. After that, it reverts back to CustomerServiceIMPL. Accordingly, the updateCustomer call is made from CustomerController. Accordingly, the value returned by CustomerServiceIMPL is returned to updateCustomer in CustomerController.
        } else {
            // If there is no such customer, I throw an exception that Java has ClassNotFoundException().
            throw new RuntimeException("no data found for that id");
        }
//        return null;
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) { // Checking by Id whether there is a customer related to the id.
            // Even there the getCustomerById() is CustomerDTO type, you cannot bring DTOs from the database.
//            Customer customer = customerRepo.getReferenceById(customerId); // The full details of the customer associated with the ID will be brought here.
            Customer customer = customerRepo.getById(customerId); // This codeline was not needed for this code but in the video it includes this code line for its code error.
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
        } else {
            throw new RuntimeException("No Customer");
        }
//        return null; // It is not needed to put a return in here.
    }

    //    Here we call the database and fetch the customers. In here, it is also same as fetching one customer like previously. Now we need to fetch the data into a list. We fetched just one object before, now we have many.
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll(); // As we cannot call the database from DTOs, we have to give Customer entity instead of CustomerDTO. customerRepo is our database. When we type . after the customerRepo, it shows us the methods with the return type of the variable. For this location, it suggests us the most relevant method at the top of the suggestion box which is findAll()    List<Customer>. There is no difference after using findAll(). All the customers in the database will come to the getAllCustomers variable. it has 10 data(assumption).
//        return List.of();
//        return null;
//        System.out.println(getAllCustomers.get(0)); // So this gets the first element from the List by calling the getAllCustomers. We do not need to mention like this with the index of the list inside the forEach (e.g.:- get(0)).
        // We use foreach loop to get the data to a list. We can loop the data in the list by using foreach loop.
        if (getAllCustomers.size()>0) {
            List<CustomerDTO> customerDTOList = new ArrayList<>();// List must be converted into the CustomerDTO type. customerDTOList is the reference needs to put the data. ArrayList<>() is assigned to the customerDTOList. But customerDTOList has no data. The data in getAllCustomers needs to be inserted into the customerDTOList which means data in the Customer of List<Customer> needs to be inserted into the CustomerDTO of List<CustomerDTO>.
//        Customer customer = getAllCustomers.get(0);

//        As the CustomerDTO is outside the forEach loop, this CustomerDTO is not recreated new every time it spins. Because the customerDTOList is created at the top. So the customerDTOList has the list of customers of type CustomerDTO.

//        forEach Loop:-
//        What happens in a forEach is that we do not give a size to operate the loop. The rounds of forEach loop is decided by the size of the reference(in here it is getAllCustomers) we have given. getAllCustomers is a list. So getAllCustomers brings all the customers in the database to the getAllCustomers. So getAllCustomers has a size that how many customers there are. It means that how many Customer entities are in the getAllCustomers. That means how many single customerDTO objects are in the Customer entity. That is the size of the getAllCustomers. getAllCustomers automatically takes the size of it, and we do not need to give it manually. But per one round, it has only one object. If we consider the first round, getAllCustomers list has the first object or the first Customer entity.

            for (Customer customer : getAllCustomers) { // Type of the getAllCustomers is Customer. So the getAllCustomers has the list of customers which the type is Customer. The first customer which comes when the for loop rotates is assigned at the beginning of the brackets like Customer customer. So now we have a problem that how the getAllCustomers list is assigned to the single Customer object? The answer is that we can assign a one customer object to the customer entity using get() method. In forEach, we do not need to give the index to execute the loop because the forEach loop mechanism has been built like that. When the forEach loop loops for the first round, first round scenario of the for loop has happened to that. When the first round is rotated, the first object of the getAllCustomers is put into the single Customer object. Then delete everything in the Customer object and add the last object of the getAllCustomers to the Customer object. When this loop rotates, Customer object was assigned each objects. But those are not exist in the Customer object. So inside the loop customer is assigned with the first value for the one time when one object rotates. So if we catch the values from customer and puts it into the customerDTOList, that's okay.
//       So what we have to do is that some data or the first data sets to the customer reference when the first round rotates. So we have to create a DTO. So now first customer's data are assigned to the customer reference in the first round. So if we put the methods of the customer reference, then the data in the customer reference has came to the customerDTO.

                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(), // But the reference is customer. customer has the data.
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumber(),
                        customer.getNic(),
                        customer.isActive()
                ); // Creates new DTO object. customer's data are put into the DTO.
//            So we can put this customerDTO into the customerDTOList. To put the customerDTO into the customerDTOList, we take the customerDTOList reference, and put it outside the customerDTO and after the customerDTO has been created and inside the forEach that reference name is written with add() with boolean type method which requires CustomerDTO type. So we need to add the customerDTO to add().
                customerDTOList.add(customerDTO); // The DTO that I added from customer to customerDTO in the forEach loop, and added it to customerDTOList. First customer's details are added into the customerDTOList in the first round. First customer's details are erased from the customer at the second round when the forEach loop enters the details of the second customer to the customerDTOList. So the second customer is added as the new object to the customer(reference). So the new object is initialized in the customerDTO reference. Previous customer is erased. So the customer reference has the second object's details. So the second object's details are inserted into the customerDTO. So the newly created DTO is put into the customerDTOList when it rotates in the second round. When the third round starts, third object is put from the getAllCustomers to the customer reference. So the previous object is erased from the customer reference. Even it is erased from the customer, that object was already sent from the customerDTOList in the forEach loop to customerDTOList where it was created initially. customerDTOList in the initial location is not recreated again and again.
            }

//        return getAllCustomers; // But we cannot return the getAllCustomers from here. We must return a DTO because the return type of the method is DTO type, but we have an entity because the database provides us entities.
            return customerDTOList; // Now there is no problem and errors.
//        So if we return the customerDTOList, we have the DTO list instead of entity list.
        }else {
//            We can return any other thing instead of throwing an exception also if we want.
//            return null;
//            throw new RuntimeException("Not Found");
//          We do not use above Exceptions because there is a standard way that is creating custom exception classes.
//          In this SpringBoot version, it did not suggest me the NotFoundException as in the video.
            throw new NotFoundException("No Customer Found"); // This calls the constructor in the NotFoundException class. As the constructor in the NotFoundException class has the String message parameter, we send the argument as "No Customer Found".
        }
    }

//  Exception handling resolves errors caused by data not being available in the database.

    @Override
    public String deleteCustomer(int customerId) {
//        return ""; // This automatically comes in this version.
//        return null; // // This automatically comes in the version of the video.
        // Check whether there is a customer related to the ID.
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId); // As the customerId is integer type, we must find the delete method can send the parameter ID in integer type.
            return "deleted successfully " + customerId; // When this value is returned from here, it goes to the deleted variable in the CustomerController class.
        } else {
            throw new RuntimeException("No Customer Found In That Id");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) { // There was an error saying that there is no parameter in the getAllCustomersByActiveState() before putting the boolean activeState as a parameter.
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState); // Data comes to the getAllCustomers after the filtering from database. So the findAll() is incorrect to do this task. Because it gives all the customers. I need to filter the customers from here. But there are no methods to filter the customers. There is a easy way to do this. As this is a find method, type the find but do not send as find only because we do not know what the database has. Just write something like findgsergnjergkrenj. Also put the activeState to it. It asks us to create this method in the customerRepo when we type this method in here because there is no such method in the customerRepo. If we type f and press ctrl + space, it suggests us the query method that we have created. Now it is correct because we have fetched the data to the Customer from findAllByActiveEquals query.
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getAllCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(), // But the reference is customer. customer has the data.
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
        // These things are common for any list which starts from the codeline List<CustomerDTO> customerDTOList = new ArrayList<>(); and ends in the codeline return customerDTOList;. This forEach loop does what come to list in entity type puts into the DTO reference of customerDTO.

//        return List.of();
//        return null; // It returns null in the video.
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
// Practising for debug will ease our work.
// So we cannot access these private variables(properties) from their names. (E.g.:- We cannot access the customerName property as the customerName(). We can only access it from getCustomerName().)