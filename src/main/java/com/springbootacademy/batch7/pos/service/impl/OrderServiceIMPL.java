package com.springbootacademy.batch7.pos.service.impl;

import com.springbootacademy.batch7.pos.dto.CustomerDTO;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch7.pos.entity.Order;
import com.springbootacademy.batch7.pos.entity.OrderDetails;
import com.springbootacademy.batch7.pos.repo.CustomerRepo;
import com.springbootacademy.batch7.pos.repo.ItemRepo;
import com.springbootacademy.batch7.pos.repo.OrderDetailRepo;
import com.springbootacademy.batch7.pos.repo.OrderRepo;
import com.springbootacademy.batch7.pos.service.OrderService;
import com.springbootacademy.batch7.pos.util.mappers.ItemMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional // When there is a transaction in the class.
public class OrderServiceIMPL implements OrderService {

    @Autowired
//    We need repo here to put this into the database.
    private OrderRepo orderRepo;

    //  Take an object of ItemMapper by @Autowired
//    @Autowired
//    private ItemMapper itemMapper;
//    itemMapper is not needed for this.

    // Autowired the ModelMapper package.
    @Autowired
    private ModelMapper modelMapper;
//  Only modelMapper is enough.

    @Autowired
    private CustomerRepo customerRepo;

//  ItemRepo needs to be called to get the item related to the ID.
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
//    We need repo here to put this into the database.
    private OrderDetailRepo orderDetailRepo;

    @Override
    @Transactional // @Transactional rolls back data from a table where data was saved if the data in the other table is not saved properly. @Transactional handles those issues automatically. We just only have to put the data.
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) { // Details in the order comes to the requestOrderSaveDTO.
//      First, we have to send the details related to the Order to the database.
        Order order = new Order(
//               requestOrderSaveDTO.getCustomers(), // Customer's ID has been come to the RequestOrderSaveDTO. This is the previous form.

//                Now Customer object is needed to be sent to the order. But I have the ID relevant to the customer. Here it calls the customerRepo to get an object.
//                customerRepo.getById(requestOrderSaveDTO.getCustomers()) , // Looking at where an entity object is returned from. getById() is available in the Spring Boot version in the video, but it has been deprecated in this Spring Boot version. getById() requires the primary key of the customer. Customer's primary key is in the requestOrderSaveDTO. getCustomers() is a int type for customerId.
//  This Above codeline was shown in the video but getById() is deprecated in the current Spring Boot versions.
    //  getById() is deprecated in Spring Data JPA 2.7+ because:
    // It returns a lazy proxy
    // It throws EntityNotFoundException later (not immediately)
    // Behavior is often confusing

                customerRepo.findById(requestOrderSaveDTO.getCustomers()).orElseThrow(() -> new RuntimeException("Customer not found!")), // This is the correct codeline for this purpose. So instead of getById(), findById() is used which requires the orElseThrow(() -> new RuntimeException("Customer not found!")).
//               findById() (SAFE & RECOMMENDED)
//                Best choice when:
                // You actually need the data
                // You want proper validation
                // You want clean REST errors

                requestOrderSaveDTO.getDate(),
//                Above methods need to put it exactly as it is in the Order class.
                requestOrderSaveDTO.getTotal()
//              Above all the data have been received from the frontend.
//              Now, all the data has been set to send for the order.
        ); // We have to put an object here.
        orderRepo.save(order); // Saving the values of the order.

//        Data in the orderDetails table will be saved only if the data in the order table are saved.

//      Checking whether the order is there.
//      order keeps the orderId there.
        if (orderRepo.existsById(order.getOrderId())){
//           There are order details related to the order if only there is an order.
//           Here the orderDetails object is taken.
//           So here we have to use either forEach or the ModelMapper.
//            List<OrderDetails>orderDetails = new ArrayList<>(); // Data in the List<RequestOrderDetailsSave> orderDetails in the RequestOrderSaveDTO must be put one by one to this List<OrderDetails>orderDetails. This is not needed now.

//          I want to convert the List<RequestOrderDetailsSave> orderDetails in the RequestOrderSaveDTO into an OrderDetails type entity.
//          The first parameter of map() contains the data. So the requestOrderSaveDTO has data in it. Now we have put all the data in the requestOrderSaveDTO by the map() into the orderDetails.
            List<OrderDetails> orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){
            }.getType());

//          We need to set the orderIds for every item in the orderDetails list. orderDetails.size() means the data count in the orderDetails. Here, what we are going to do is that to check how many data in the orderDetails. All the data must have the same orderIds. Details of the particular orderId must be applied to the rest of the data in the orderDetails.
            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order); // Set the orderId to the i. There it has been put the order to set the orderId. Then it directly catches the orderId. When we put order here, we do not to take it separately because it was already there.
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems())); // In the current versions, getById() was deprecated and findById() is used instead of getById().
            }
            if (orderDetails.size()>0){
//               We do not need to save the requests one by one. There is a method for that.
                orderDetailRepo.saveAll(orderDetails); // saveAll() can save all the data in an iterable list that contains more than one data item at once.
            }
            return "saved"; // This return statement must be here.
        }
//        return ""; // It is an empty string for this Spring Boot version.
//        return null; // It is a null for Spring Boot version in the video.
//        return "saved"; // This is newly returned data. This return must not keep here.
//        throw new Exception("error"); // This showed an error.
        return null;

    }
//  Above complete code inside the addOrder() is the Transaction.

//    There the data must go to the orders and order_details tables.
}
