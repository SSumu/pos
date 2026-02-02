package com.springbootacademy.batch7.pos.controller;

import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch7.pos.service.OrderService;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(
            path = {"/save"} // This can be written inside {}.
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
//        System.out.println(requestOrderSaveDTO); // Send the values relevant to the requestOrderSaveDTO.
        String id = orderService.addOrder(requestOrderSaveDTO); // orderService is called from here. Data are received through requestOrderSaveDTO.

        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201, 2 + "item successfully saved", 2), // This is the previous form of this codeline.
                new StandardResponse(201, id + "item successfully saved", id), // This is the new form of this codeline.
                HttpStatus.CREATED
        );

    }
}
