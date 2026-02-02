package com.springbootacademy.batch7.pos.dto.request;

import com.springbootacademy.batch7.pos.entity.Item;
import com.springbootacademy.batch7.pos.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {
    private String itemName;
    private double qty;
    private Double amount;
    private int orders; // So this also has an ID in the orders. Such kind of orders is not sent by the frontend. So this must be removed and we have commented it here.
    private int items; // Also, only ID is sent to the item. items are sent by the frontend. This sends an ID.

//    There is a list from the above variables in the order. It is an item list. It is an item id list.
}
