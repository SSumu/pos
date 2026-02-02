package com.springbootacademy.batch7.pos.dto.request;

import com.springbootacademy.batch7.pos.entity.Customer;
import com.springbootacademy.batch7.pos.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private int customers; // An ID comes from the frontend for the customers even the Order class expects an object for the customers.
    private Date date;
    private Double total;
//    private Set<OrderDetails> orderDetails; // We do not write like this.
    private List<RequestOrderDetailsSave> orderDetails; // We write like this. It is not to the OrderDetails entity. It is a list as a RequestOrderDetailsSave DTO. So items come as a list. customers, date and total are the unique data for the order. ID is in the customers variable. orderDetails means the List<RequestOrderDetailsSave> orderDetails.
}

// Even we mapped the objects in the order, but the frontend sends an ID related to the customer which is currently in the database, really it is not an object.