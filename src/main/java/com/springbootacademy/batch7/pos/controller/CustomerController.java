package com.springbootacademy.batch7.pos.controller;

import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.batch7.pos.entity.Customer;
import com.springbootacademy.batch7.pos.service.CustomerService;
import com.springbootacademy.batch7.pos.service.impl.CustomerServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
// We use @RestController otherwise we have to use above two annotations separately. Both of the above annotations are present in the @RestController so it is used. Here the Rest means external origin. Rest api means the request comes from another origin or another component. It is not inside this project, and it is from external server or another origin. The exact correct word is from another origin.
@RestController // This says that this is a RestController. So this becomes a controller by using annotation. This is what makes it possible for the Client-Server architecture, or REST API, to generate a response when a request is sent, and this defines it as a controller by this annotation. This RestController annotation is made from two annotations which are Controller annotation + ResponseBody annotation. So this creates the relevant environment, called a controller. Rest means this can take a request comes from outside. REST API is what allows to receive requests from other external component.
// We receive requests from another component in the form of JSON via HTTP requests. HTTP requests come from the address bar on the browsers.
@RequestMapping("api/v1/customer") // This will unique the CustomerController. These are standards which is inside the "". This is unique because of "api/v1/customer". This is the link of the CustomerController.
@CrossOrigin // This is typically applied. This gives us some default protections from Spring Security. There are types called COURSE and CSRF. CSRF is really a security attack. COURSE is not really a security attack. The COURSE is actually from another origin, now you can even send requests to this backend using Postman. But it's a problem that someone who has this link in a backend can send requests to this backend from anywhere. This backend must only receive the requests from the frontend only related to this backend. If domain is written inside the "" inside the brackets, requests only from that domain will be taken. But if there is no brackets, it means that take any requests from any origin.
// Once a domain is given, no one else can get it.
public class CustomerController {
//    This is wrong, and we cannot give like this. Because we did not put the Customer in the container.
//    @Autowired
//    private Customer customer;

    @Autowired // We're keeping this annotation at the top because we will need it in the future. By using this annotation we inject the object otherwise the bean in the container to the class when we need that object otherwise bean. This is a property injection. This is the mostly used one. There are several ways to Autowired which are setter method injection, property injection, constructor injection but this is the mostly commonly used method. So the annotation was injected to the property.
//    @Qualifier() // By using this annotation, there it can define which object it needs when there are several classes or interfaces implemented by each other. There we have to define inside () that which object of class it needs. E.g.:- There the CustomerServiceIMPL class implements the CustomerService interface and CustomerService interface is implemented by another class. If both classes requested the data, to which object that requested data will come. So then the requested data goes to the object which was defined inside the () in this @Qlifier.
    private CustomerService customerService; // customerService is the reference.
    // We define an object in the container and put it in the container as an object during the initialization stage, Autowired it, and inject it into our class, which is what we call Dependency Injection or DI.
    // So the above two codelines are the Dependency Injection or DI.

    // As soon as a request comes in from the frontend, it should come here.
    // Customer's dto can be applied to here as a return type or any return type can be applied to here.
    // A return type is the type created of response that is sent in response to an incoming request.
    // The Return type is the type of something that is returned when it exits the method. If there is no return type, we can use void. Returning means the data must be returned according to the return type.

//    @PostMapping("/save-1") // This annotation tells that this method saves data to the database. Then this method is now unique. Some part of the link is given inside the brackets to unique the link. Because there are two PostMappings. If there are several methods, each method needs to be unique.
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){ // The JSON object that comes in the HTTP request is captured here as a parameter. customerDTO is the reference of CustomerDTO. @RequestBody is to convert the JSON object into the Java class. JSON object comes through the HTTP request from the frontend needs to be bound to the CustomerDTO class type. So that incoming data (as JSON object) needs to bind(convert) to my DTO. @RequestBody binds(converts) the incoming JSON object to the class name of the DTO. Both JSON object and DTO must have the same variable names to map both of them. @RequestBody binds(converts) the JSON object(type) to the CustomerDTO class type. A request from external server is taken inside and the JSON type is bound to the class type by @RequestBody. But it cannot send something that is sent from the inside to the outside as a JAVA type. It has to send it as a JSON type. It fulfills the client-server architecture after this goes to the frontend. Client-Server architecture simply means generating a response to a request from another origin and sending it to the frontend. customerDTO is a Java object of the CustomerDTO class. @RequestBody converts the incoming JSON object(keys in the object) to the variable types of the Java object(customerDTO) of Java class(CustomerDTO). Then I can use the details of the customerDTO from the customerDTO reference inside. Then the data of the message variable is printed on the console. @RequestBody maps the data types of Java class dto type and object type. Also, the frontend does not support the Java. Now the customer's details needs to be saved in the database has come to the customerDTO. In here, it sends all the data of all fields in CustomerDTO.
//        String message = "saved"; Now this is not needed.
        // Now these codelines are not really needed.
//        String message = customerDTO.getCustomerName(); // Reference has so many values and here it takes the name value only. Values get through the reference.
//        System.out.println("Come "+message);
//        return message; // message is the response needs to be bound(converted) to the JSON because the message has been created from JAVA which needs to be sent as JSON. It leaves here. The @ResponseBody inside @RestController makes the message sent as a JSON. This returns the Java based response. @ResponseBody converts the response of message has been created from Java to the JSON.
//        return "";
        // Accessing a method in the service class from the controller class. Accessing means passing the CustomerDTO data to the method in the service related to saveCustomer, and the service class will do the rest. All we have to do is provide the values in the customerDTO. So, there should be a method in the service class that corresponds to the return type of the method in the controller class in the way that incoming parameters of this CustomerDTO customerDTO can be captured.

        // If we code in this way, there we have to create many objects but there is a limit in creating objects. When so many objects are created, the Heap is full then the project is stuck. So below way is not used.
//        CustomerServiceIMPL customerServiceIMPL = new CustomerServiceIMPL();
//        customerServiceIMPL.saveCustomer(customerDTO);
        customerService.saveCustomer(customerDTO); // customerDTO is the data what we expect. customerDTO is a CustomerDTO parameter type.
        // Here we pass the data from CustomerController layer to CustomerService layer. (Passing the data from Controller layer to Service layer). By using dependency injection, object of the service class is injected to this class, through the reference of the object, calling the object's method, data from the frontend convert to the class type object and pass that class type object to the customerDTO inside the saveCustomer().
        return "saved";
        // So there is design pattern is used to do this function which is singleton design pattern. But in Spring, there is a concept called Dependency Injection which is very similar to singleton design pattern.
        // So here we use Dependency Injection design pattern to call among the classes.
        // What is returned from here is what is displayed on the frontend. It is not what is returned from the service is displayed on the frontend.
        // But if I want, I could have created a CustomerDTO from here and sent that saved CustomerDTO to the frontend. But this is not needed.
    }

//    @PostMapping("save-2") // The link is unique by the part inside the "" inside the brackets.
//    public String saveCustomerWithuyyt(){
//        String message = "saved2";
//        return message;
//    }

    @PutMapping("/update") // Unique the method by putting the /update.
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){ // But in an update, we can decide which fields that we should give to update. So it is useless to send the whole CustomerDTO to here. So we have to create a separate one with Id, Name, Address and Salary. But the Id is not sent inside this like other ones, and it must be separately sent as a parameter. But in this stage, we send it inside with them.
//        We can use the whole CustomerDTO without any problem but the reason for not using the whole CustomerDTO is Let's think like this: Let's think that sending a DTO like this with unnecessary fields with null values means that these values comes through an HTTP request. These HTTP requests come through internet to servers. So the load is increased when the incoming HTTP requests are increased. Then, if we only fetch the data values of the required fields, the performance of these requests going over the internet would be even better. So we create a small package named request in the dto package.
//        After the CustomerUpdateDTO is created, the whole DTO is not come to here. The frontend only sends me the fields of the DTO that are relevant to the request. The CustomerUpdateDTO is what it needs to catch from the Controller and send from the frontend. Then, the frontend will only send the data that is currently in the customerUpdateDTO which is related to the 4 fields in the CustomerUpdateDTO.
        // As usual, as the data sent from controller to the service:-
        String message = customerService.updateCustomer(customerUpdateDTO); // Reference of the service object. If I'm correct, I'm accessing something in the customerService interface from the CustomerController. The data from the frontend which customerUpdateDTO is put inside the () of updateCustomer. Actually we think that we created the updateCustomer() method in the customerService interface. But there is no updateCustomer() method in the customerService interface. After dependency injection, we access the updateCustomer() method through the object reference. Even if it is not available in the customerService interface, the method can be created with the help of IntelliJ tool. If we hover over the updateCustomer() method, IntelliJ tool ask us to create the updateCustomer() in the CustomerService interface. When we click it, IntelliJ tool creates the updateCustomer() with matching the return type and parameters in the CustomerService interface.
        // The String returned by the updateCustomer in the CustomerServiceIMPL calls this updateCustomer in the customerService as well as if we assign the String value returned from CustomerServiceIMPL to this updateCustomer in the customerService, then we can catch the value.
//        return "updated";
        return message; // Then this returns the returned value of the updateCustomer in the CustomerServiceIMPL.
// In this update, it was also said that it would not be sent inside the DTO, but as a separate parameter.
    }

    // Search. We need to get the ID from the frontend and get the customer associated with that ID.
    @GetMapping(
            path = "/get-by-id", // path is a variable. get-by-id means get the data from this id.
            params = "id" // It is sent as the param or the parameter. params is a variable. That means that when calling or addressing this path(/get-by-id), it must send a parameter called id.
    )
    // So now I cannot send whatever I want to the frontend. I send an ID, it asks me for a response related to that ID, related to the customer I want to send it to.
//    public CustomerDTO getCustomerById(int id){} // With the previous parameter
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){ // Id is sent from the frontend and I catch it in here as customerId. If it sends us the ID and ask us to send the customer, then what should be the return type of the getCustomerById()? It sends the Id and requests the entire customer object which includes the ID, Name, Address, Salary, nic and active state. The correct answer is CustomerDTO. Data flow from the Controller layer to the Service layer occurs only through DTO and Entity cannot be used at all. The reason I am using CustomerDTO here is that there is only one customer associated with the ID sent to me from the frontend, that is, the primary key of that customer. Then I need to set the data coming from the database to a DTO and send it to the frontend. That cannot be done with a String. String means that only one String value. The entire entity must be sent. But just as we cannot send a DTO from the Service layer to the Repository layer when saving, we cannot send an entity from the CustomerService to the CustomerController when responding from the database. There are two ways to send parameters from the frontend. It needs to find out if there is more than that. One is as a request param or parameter. Otherwise, as a path variable.
//   If you give the params an id, Postman will send it to you with the name id, so you must have the same name when you grab it here. Otherwise, it will not map it.
//   If the frontend sends the params as id and the backend names the parameter in the method as customerId, that is a problem.
//   If the params value in GetMapping and the parameter name in the method are different, the value of params can be captured by using the RequestParam annotation with its value variable with the value before the parameter name in the method. So then the value sent to params from the frontend is captured using the @RequestParam(value = "id") in the getReferenceById method. After the value is captured from the @RequestParam(value = "id"), the value is now passed to customerId from @RequestParam(value = "id"). If both the name in the params in the GetMapping and the parameter name in the getCustomerById are equal, then the @RequestParam(value = ) is not needed. But it is a good practice to apply this @RequestParam(value = ).

        // Let's see how to send the ID as a request parameter.

//        System.out.println("Print value "+ customerId);
//        System.out.println("Print value "+ customerId);
        CustomerDTO customerDTO = customerService.getCustomerById(customerId); // customerDTO is the reference. Here, for the CustomerDTO reference, the frontend needs to return CustomerDTO data. customerId is the value from the frontend.
//        As soon as the customerDTO is returned from getCustomerById() in the CustomerServiceIMPL, the customerDTO is assigned to the location where getCustomerById() was called in the CustomerController. That is, as soon as it is returned, it is assigned to the customerDTO in getCustomerById() in here.
//        return null; // This is needed to run the method.
        return customerDTO; // Return the value has been come the customerDTO reference. CustomerDTO type should be returned only. Other types cannot be returned from here.

//   Now we need to send the actual value to the database and give the customer associated with the actual value to the frontend. The data returned from the method is the same type as the data returned from the frontend. Just as you would get String data to return a String result, you would get CustomerDTO data from the frontend to return a CustomerDTO result from the method.
    }

    @GetMapping(
//            path = {"/get-all-customers"}  // As all the customers are fetched, id is not needed. As the id is not used here, param is not used. If we need, we can give the path inside {} like this.
            path = "/get-all-customers" // Otherwise, it can keep without {} brackets and it is not incorrect.
    )
    public List<CustomerDTO> getAllCustomers(){ // CustomerDTO brings only one customer object. As we need all the customer objects, we need a List. So the type must be the List of CustomerDTOs. So the type is List<CustomerDTO>. <> is the Generics. So we want the List of CustomerDTOs. So we have written the type of the List inside the Generics<> which means this is a CustomerDTO type List. If we want to send all the customer objects in the database, we have to take those all customer objects into the CustomerDTO type object. So this List is used to keep list, collections. So we can keep so many CustomerDTO type lists.
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();// So as usual, if the return type is mentioned as List<CustomerDTO>, we definitely have to fetch the data in that type from anywhere. allCustomers is the reference in List<CustomerDTO> type. Then we call the customerService. So do we need to send something inside parameters? It is not needed give any parameter as we do not filter anything. As we get all the customers, it is not needed to give any condition like in the getCustomerById(). All the customers means active and inactive or customers in any type.
//        return null;
        return allCustomers; // So we can return the allCustomers without any problem. If allCustomers receives data, allCustomers will match the List<CustomerDTO> type when returned from here. Then it will goes to frontend.
    }

    // This is the another way of sending the parameter which is using path variable.
    @DeleteMapping(
            path = "delete-customer/{id}" // In this path variable method, id is sent through this path like this. ID needs to be sent through the {} to catch the id.
    )
    // In this method, we do not need return anything to the frontend, but we only have to return a String saying that customer's data were deleted.
//    public String deleteCustomer(@PathVariable int id){}
    public String deleteCustomer(@PathVariable(value = "id") int customerId){ // If the variable name of the ID in the path variable and the variable name of the ID inside the () are same, we can put as id name with the variable type. But if those are different, there it has to mention the (value = "id"). We say that if there is any property with value id, that value must be assigned to the customerId. So the value in the value variable will be assigned to the customerId.
        String deleted = customerService.deleteCustomer(customerId); // We have to pass the customerId to delete the customer related to the ID. Returned value from the deleteCustomer will be assigned to this deleted variable.
        return deleted; // Return the value the String value to the frontend.
    }

    // When it requests for the true or false in active_state of the customers, we must think that there may be more than one customer for the true or false in active_state. So then the return type of the method needs to be List type.
    @GetMapping(
            path = "/get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status") boolean activeState){ // @PathVariable catches the value comes from the link to the getAllCustomersByActiveState(). So the value must be put into the activeState as it is a boolean true or false value. As this activeState is a boolean type with @PathVariable, it has two values of true and false only. So in the Swagger U I where it gives us those values in the dropdown.
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState); // The activeState needs to be sent throughout this getAllCustomersByActiveState(). The error in the activeState inside the getAllCustomersByActiveState() means that there is no parameter in this method in the CustomerService class.
        return allCustomers;
        // If we put a debugger point to allCustomers line and run in debugger mode, it shows us the stopped position in the running process, and it shows us the value of the activeState in the @PathVariable codeline was assigned to the activeState in the customerService codeline.
    }
}


//localhost:8090/api/v1/customer
// In Spring, we should tell the HTTP request type after the method was created.
// HTTP request types:-
// post - save - If we save data in the database.
// get - search - If we get data from database by searching it. If we get all data from database, it is also the same.
// put - update - If we want to update existing data.
// delete - remove - If we want to remove existing data.