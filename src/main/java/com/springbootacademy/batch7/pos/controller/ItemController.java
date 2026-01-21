package com.springbootacademy.batch7.pos.controller;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.service.ItemService;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired // As we go to service from controller, we have to Autowired the service class here.
    private ItemService itemService; // We have to create a service to Autowired the service class.

    // If we are going to save an item, it must be PostMapping.
//    @PostMapping(
//            path = {"/save"} // This can be written inside {}.
//    )
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){ // ItemSaveRequestDTO is the data that we need to get from the frontend to here.
//        String message = itemService.saveItem(itemSaveRequestDTO); // itemSaveRequestDTO data are sent to the itemService.
//        return "saved"; // This return data must be matched with the data type of the method.
//    }

    @PostMapping(
            path = {"/save"} // This can be written inside {}.
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){ // ItemSaveRequestDTO is the data that we need to get from the frontend to here. ResponseEntity from http package to send a standard response. So we can create a DTO as our need and pass that DTO as generics to the frontend. <StandardResponse> is a generics. That means the type of ResponseEntity it receives at that time is based on the data we provide.
        String message = itemService.saveItem(itemSaveRequestDTO); // itemSaveRequestDTO data are sent to the itemService.
//        ResponseEntity responseEntity = new ResponseEntity();// There it must return a ResponseEntity. As the method's return type is ResponseEntity, it must return a ResponseEntity( It must return a ResponseEntity type reference which is responseEntity. ). There this codeline has created an object. So responseEntity is an object.

//        ResponseEntity responseEntity = null; // responseEntity was assigned with null just for this moment.
//        return responseEntity; // This return data must be matched with the data type of the method.

//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message), HttpStatus.CREATED // Create a new object from StandardResponse. For the save/created status code is 201. message is the object.
//        ); // response is the reference. There it gives an object inside (). It must give a data from standard response. Here the StandardResponse is instead of T in the constructors in the ResponseEntity.class. T in the constructors in the ResponseEntity class is generics type. T body means generic body. StandardResponse's type sets instead of T. So the response is the object which should be returned.

//        return response;

//        Typically, there it does not write the code like above, and it is written and returned from the same codeline. It is not needed to assign an object.

//      The above ResponseEntity code block is written and returned like this way without assigning with an object.
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED // Create a new object from StandardResponse. For the save/created status code is 201. message is the object.
        );

    }
//  For a standard ResponseEntity, it sends three parts which are code,message,data(object or any other type).
//  ResponseEntity data type method is the industry standard.

//    When we search for active items, we just only want to send the itemName as a parameter and do not need to send the activeState of the item from the frontend. Since we are searching for active items, we already know the value of the active state. I want to search for the item is active or not.
    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    // It is better to keep a list here because there may be two items with the same name. So we must definitely keep a list. When we return this with ItemGetRequestDTO type, it returns the data without the ID and activeState.
    // ItemGetResponseDTO list type must be placed here.
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){ // @RequestParam has been put to catch the request param. I take the name request param into the itemName variable. To send this out, I want to create an itemDTOS in List<ItemGetResponseDTO> type.
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName); // Search from the itemName has been come from the frontend.
        return itemDTOS;

    }

    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params = "name"
    )
    // It is better to keep a list here because there may be two items with the same name. So we must definitely keep a list. When we return this with ItemGetRequestDTO type, it returns the data without the ID and activeState.
    // ItemGetResponseDTO list type must be placed here.
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName){ // @RequestParam has been put to catch the request param. I take the name request param into the itemName variable. To send this out, I want to create an itemDTOS in List<ItemGetResponseDTO> type.
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusBymapstruct(itemName); // Search from the itemName has been come from the frontend.
        return itemDTOS;

    }

    @GetMapping(
            path = "/get-all-item-by-status",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemsByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size // This @Max(50) is a validation means that the maximum data count on the page is 50. If it allows the user to input the value for size, it is better to give this @Max() with its count. Otherwise, if the user input a very huge count, then it will be a high data load for the page. This @Max(50) was executed like this in the video, but it was not executed to me like in the video. Because in this Spring Boot version, the validation needs a dependency. spring-boot-starter-web NO LONGER includes validation. But in this Spring Boot version,
            ){
//        List<ItemGetResponseDTO> itemDTOS = itemService.getItemsByActiveStatus(activeStatus,page,size); // Search from the  has been come from the frontend. This is the new form.
//        List<ItemGetResponseDTO> itemDTOS = itemService.getItemsByActiveStatus(activeStatus); // This is the previous form. Previously there were no page and size parameters.
//        size = 10; // We can give the fixed value for size like this. Then the size value has been come from the frontend is not taken. Always the value of size is this value. (e.g.:- When we search from Google, it gives us 10 records for a page.)
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size); // paginatedResponseItemDTO is the reference.
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK // Create a new object from StandardResponse. For the get, status code is 200. Status code is 200 for GET request. allCustomers is the object containing all the customers to be returned.
//               paginatedResponseItemDTO must go as the response.
        );

    }


}
