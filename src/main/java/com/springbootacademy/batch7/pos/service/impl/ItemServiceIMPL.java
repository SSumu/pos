package com.springbootacademy.batch7.pos.service.impl;

import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.entity.Customer;
import com.springbootacademy.batch7.pos.entity.Item;
import com.springbootacademy.batch7.pos.entity.enums.MeasuringUnitType;
import com.springbootacademy.batch7.pos.repo.ItemRepo;
import com.springbootacademy.batch7.pos.service.ItemService;
//import com.springbootacademy.batch7.pos.util.mappers.ItemMapper;
import com.springbootacademy.batch7.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
//    We need repo here to put this into the database.
    private ItemRepo itemRepo;

    // Autowired the ModelMapper package.
    @Autowired
    private ModelMapper modelMapper;

//  Take an object of ItemMapper by @Autowired
    @Autowired
    private ItemMapper itemMapper;
//    private ItemMapper m;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        // What we do here is that we put the data came from the DTO into the entity.
        // All the data needs to be put into the entity.
//        Item item = new Item( // item is an entity object.
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringUnitType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
////      These are the values has been come from the frontend which we access from here. Manual values means when adding an item for the first time, and it is a business requirement of the seller, the item must be in active state. So we say that item is not available. saveItem means the location where adding item for the first time. For the first time it is definitely not changed.
////      But we do not know how many fields in the form. If there are so many data(fields) (like 100 data in video), it takes long time. This issue arises when I try to insert data into this on the frontend. We use easy method to do this in modern technology which is Mappers. There are two main Mappers and need search that there are any other Mappers. Those two mappers are Model Mapper and Map Struct. Model Mapper means that it allows us to use predefined methods and Map Struct means we can create customize methods.
//        );
//        itemRepo.save(item);
//        return item.getItemName();
//        return null;
//        return "";
//        Item item = modelMapper.map(itemSaveRequestDTO,Item.class); // Use the modelMapper reference. (L,R) :- L = First one is the object with data. The data comes from controller through the itemSaveRequestDTO. So data are in the itemSaveRequestDTO. We need to put the data into the Item entity type from itemSaveRequestDTO. If the data are in itemSaveRequestDTO, it should be placed in the L position first. R = Indicates what the conversion should be to. When we put Item to the right side, it must be Item.class. Item.class says that the data in the itemSaveRequestDTO should be converted to data in the Item class. ID is not required since ID is auto-generated.
//        item.setActiveState(true); // But if we do not give the activeState, then it goes as false. It does not matter when the activeState is false. But if we need to send true, we can change this with taking the item reference. We can set the true if we want to send true. But we do not need to send the activeState as true because item must be in false level when I add the item. So we put the data into the item.
//      This is how to do this using itemMapper.
        Item item = itemMapper.dtoToEntity(itemSaveRequestDTO); // What I do here is that data come to item from ItemSaveRequestDTO type. I have the ItemSaveRequestDTO type so it needs to put ItemSaveRequestDTO type into the item. I can use dtoToEntity() here which requires the ItemSaveRequestDTO type. Both modelMapper and itemMapper are same.
        if(!itemRepo.existsById(item.getItemId())){ // Checking such an ID is there. If there is no such an item, then save the new item.
            itemRepo.save(item); // Sending the item reference to save the new item.
            return item.getItemId()+" saved successfully"; // Otherwise let's return itemId.
        }else {
            throw new DuplicateKeyException("Already Added"); // Throwing new exception.
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
//        return List.of(); // Came as default.
//        return null; // This had been come in the video.
//        We have to write the logic in here.
//        But we cannot get the data into this when we deal with the database even this is the list item.

        boolean b = true; // If the activeState is true, take it to the b variable.
//        List<Item> items = itemRepo.fwegwregjgergher(itemName,b); // We have to write a search query to filter. Write something like fwegwregjgergher. We have to pass the itemName inside the fwegwregjgergher() to create the method. We also have to filter from the status. We have to give it manually, or we can give it from there. b is sent with the itemName. But we can give true instead of b inside the fwegwregjgergher().
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b); // After the findAllByItemNameEqualsAndActiveStateEquals was created in the ItemRepo, it is applied to here.
        if (items.size()>0){ // items.size() means there are data otherwise there is no data.
//            return items; // We cannot give like this.
            // We have to convert to ItemGetResponseDTO type. We have to create a list in that type.
//            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items,ItemGetResponseDTO.class); // itemGetResponseDTOS is the reference. (L,R):- Left side is the list with data has been come from the database. Right side is the type must be returned. There are several types when we work with modelMapper. We can use the modelMapper to put one DTO into another entity. But in here what happens is that list is converted into the entity list. That means we have to put the entity type data into the DTOList. Like in the foreach. But it does not work in here. The code changes a little. So the above code is wrong it must be changed like below.
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType()); // <> :- There it needs to mention inside <> of the List<> that this List is from which type. ItemGetResponseDTO needs to be placed first inside the <>. getType() is there to assign to that. This is the way how list is converted to entity from dto. Left side what it needs to be. If we copy this code line new TypeToken<List<ItemGetResponseDTO>>(){}.getType() , then we only have to change the ItemGetResponseDTO. This is the way how list is converted to both sides.
            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Item is not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {
//        return List.of(); It returns List.of() in the SpringBoot version in this project.
//        return null; // It returns null in the SpringBoot version in the video.

        boolean b = true; // If the activeState is true, take it to the b variable.
//        List<Item> items = itemRepo.fwegwregjgergher(itemName,b); // We have to write a search query to filter. Write something like fwegwregjgergher. We have to pass the itemName inside the fwegwregjgergher() to create the method. We also have to filter from the status. We have to give it manually, or we can give it from there. b is sent with the itemName. But we can give true instead of b inside the fwegwregjgergher().
//      This method is same for both modelMapper and mapStruct.
//      I have a List in Item entity type and I want to convert it to the ItemGetResponseDTO type.
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b); // After the findAllByItemNameEqualsAndActiveStateEquals was created in the ItemRepo, it is applied to here.
        if (items.size()>0){ // items.size() means there are data otherwise there is no data.
//            return items; // We cannot give like this.
            // We have to convert to ItemGetResponseDTO type. We have to create a list in that type.
//            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items,ItemGetResponseDTO.class); // itemGetResponseDTOS is the reference. (L,R):- Left side is the list with data has been come from the database. Right side is the type must be returned. There are several types when we work with modelMapper. We can use the modelMapper to put one DTO into another entity. But in here what happens is that list is converted into the entity list. That means we have to put the entity type data into the DTOList. Like in the foreach. But it does not work in here. The code changes a little. So the above code is wrong it must be changed like below.
//          We can customize the methods in the MapStruct.
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items); // This method requires a List type in Item entity. So items is the List type in Item entity. As soon as I put items inside the (), it converts the items into the List<ItemGetResponseDTO> type.
//            List<ItemGetResponseDTO> itemGetResponseDTOS =null; // This is for the problem in the video.
            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Item is not active");
        }
    }
}

// So we need to configure the Model Mapper and then put it into the container. We must add Model Mapper as a dependency.
// TypeToken a compiler token as a basic program element, and a TypeToken class (from libraries like Guava or Gson) used to work with generic types at runtime. TypeToken keeps the list type of the new list as generics and give that new list type to each list item when modelMapper spins the list items of the old list. When the modelMapper spins, TypeToken creates a new object for each round.