package com.springbootacademy.batch7.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
    public class ResponseOrderDetailsDTO {
    // customer
    private String customerName;
    private String customerAddress;
    private ArrayList<String> contactNumber;

    // order
    private Date date;
    private Double total;
}

// Data comes from two tables of customer and order. It is going to pull the data from all the tables ( two tables in here ) by running a query once.