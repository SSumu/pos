package com.springbootacademy.batch7.pos.controller;

//import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch7.pos.service.OrderService;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import jakarta.validation.constraints.Max;
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

//  This is for the Join Query.
//  Now what happens here is, if send the stateType as active, then I need to send all active orders. If I send the stateType as inactive, then I should send the inactive orders.
    @GetMapping(
            params = {"stateType","page","size"}, // String in a State type.
            path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size

    ){

        PaginatedResponseOrderDetails p = null;
        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){ // equalsIgnoreCase means that it ignore the simple and capital case.
            boolean status = stateType.equalsIgnoreCase("active") ? true : false; // The stateType in the database is boolean type.
            p = orderService.getAllOrderDetails(status,page,size);
        }

        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"SUCCESS",p),
                HttpStatus.OK // This is the status type.
        );

    }


}
