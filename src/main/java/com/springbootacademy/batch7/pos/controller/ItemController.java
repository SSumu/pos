package com.springbootacademy.batch7.pos.controller;

import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired // As we go to service from controller, we have to Autowired the service class here.
    private ItemService itemService; // We have to create a service to Autowired the service class.

    // If we are going to save an item, it must be PostMapping.
    @PostMapping(
            path = {"/save"} // This can be written inside {}.
    )
    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){ // ItemSaveRequestDTO is the data that we need to get from the frontend to here.
        String message = itemService.saveItem(itemSaveRequestDTO); // itemSaveRequestDTO data are sent to the itemService.
        return "saved"; // This return data must be matched with the data type of the method.
    }

//    When we search for active items, we just only want to send the itemName as a parameter and do not need to send the activeState of the item from the frontend. Since we are searching for active items, we already know the value of the active state. I want to search for the item is active or not.
    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    // It is better to keep a list here because there may be two items with the same name. So we must definitely keep a list. When we return this with ItemSaveRequestDTO type, it returns the data without the ID and activeState.
    // ItemGetResponseDTO list type must be placed here.
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){ // @RequestParam has been put to catch the request param. I take the name request param into the itemName variable. To send this out, I want to create an itemDTOS in List<ItemGetResponseDTO> type.
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName); // Search from the itemName has been come from the frontend.
        return itemDTOS;

    }

    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params = "name"
    )
    // It is better to keep a list here because there may be two items with the same name. So we must definitely keep a list. When we return this with ItemSaveRequestDTO type, it returns the data without the ID and activeState.
    // ItemGetResponseDTO list type must be placed here.
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName){ // @RequestParam has been put to catch the request param. I take the name request param into the itemName variable. To send this out, I want to create an itemDTOS in List<ItemGetResponseDTO> type.
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusBymapstruct(itemName); // Search from the itemName has been come from the frontend.
        return itemDTOS;

    }


}
