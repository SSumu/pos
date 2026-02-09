package com.springbootacademy.batch7.pos.repo;

import com.springbootacademy.batch7.pos.dto.queryinterfaces.OrderDetailInterface;
import com.springbootacademy.batch7.pos.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.batch7.pos.entity.Item;
import com.springbootacademy.batch7.pos.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order,Integer> {

//    This is a Join query.
//    @Query(value = "select * from customer c,orders o",nativeQuery = true)
 @Query(value = "select c.customer_name as customerName , c.customer_address as customerAddress  , " + "c.contact_numbers as contactNumber , o.order_date as date, o.total as total from customer c,orders o where o.active_state = ?1 " + "and c.customer_id = o.customer_id",nativeQuery = true)
//@Query(value = "select  from customer,orders o",nativeQuery = true)
// When we are writing native query ( handwritten query without using the spec in Spring Boot), @Query is used. We must inform this application that we are going to write a native query. By default, nativeQuery is false. c is a customer object. Tables' names must be as those names in the database. But there is no use from *. It can be directly caught from the customer without c. But with the reference c, it is easy to catch it. The column names must be the same as it is in the database. In here, those column names are customer_name, customer_address which is in the customer database. o is the object of the orders. o.active_state = ?1 It's there because I filter and request it from the frontend. ?1 means that 1 must be assigned to the status because status is the first parameter. customer_id in the orders table and customer_id in the customer table must be equal because data can only be sent if the customer_ids in both tables are equal. c means customer and o means orders. So c.customer_id means customer.customer_id.
//    List<ResponseOrderDetailsDTO> getAllOrderDetails(boolean status, PageRequest of); // There it is not PageRequest, and it must be Pageable like below. This is the previous form.
//    List<ResponseOrderDetailsDTO> getAllOrderDetails(boolean status, Pageable pageable); // So it must be like this Pageable pageable. This is the previous form and ResponseOrderDetailsDTO Generic type is incorrect.
 List<OrderDetailInterface> getAllOrderDetails(boolean status, Pageable pageable); // This is the new and correct form.

// After data arrives from OrderRepo, that data arrives in OrderDetailInterface. It comes from OrderDetailInterface into getAllOrderDetails(). When calling the OrderRepo method and passing data, we need to tell it to return the value as it is in the OrderDetailInterface interface.
// Using as in the query, I am saying that when it comes from customer_name, this column should come as customerName instead of like this customer_name. So that means all the method names must be there in the query with the basic form of the methods.

// Below methods were brought to here for ease of viewing. Below methods must be removed.
// String getCustomerName();
//
// String getCustomerAddress();
//
// ArrayList<String> getContactNumber();
//
// Date getDate();
//

// Double getTotal();
// Not in this Type method form of the methods. We need these methods which had the previous form without Type method.


 @Query(value = "select count(*) from customer c,orders o where o.active_state = ?1 and c.customer_id = o.customer_id",nativeQuery = true)
long countAllOrderDetails(boolean status);
}
