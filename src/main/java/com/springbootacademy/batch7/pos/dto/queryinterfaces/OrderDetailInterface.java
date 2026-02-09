package com.springbootacademy.batch7.pos.dto.queryinterfaces;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {
//  These are the type methods.
    String getCustomerName(); // To create a type method, we cannot give names for the method as we like. The method name must be in this form.

    String getCustomerAddress();

    ArrayList<String> getContactNumber();

    Date getDate();

    Double getTotal();

//    There it cannot create variables in here like this in the interface. But there can be methods.
//    Variables in the ResponseOrderDetailsDTO must be written in here.
}
