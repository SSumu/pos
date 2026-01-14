package com.springbootacademy.batch7.pos.util.mappers;

import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // The Mapper must be from mapstruct. componentModel is a variable.
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items); // I want to convert an ItemList into an ItemResponseDTO. As we want a list, enter the required type(ItemResponseDTO) inside the <> as a list. This converts the entity list into the dto list. An interface can only contain methods without a body. Object with the data must be entered inside the () of the method. List<Item> items type is having the data is applied inside the () as parameter. Putting this type as a parameter means that if this method is called anywhere, it must pass a value that matches and fulfill this parameter.
//  Not like in the model mapper. Inside the mapper, the data are placed on the right side which is List<Item> items. What you need to do is placed on the left side which is ItemGetResponseDTO.

    ItemGetResponseDTO entityToDto(Item item); // If only DTO is needed, it is like this.
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO); // This converts the dto to entity(Item). What needs to be done is to convert it to an Item type. So what needs to be done is to convert the DTO has been come from the frontend to the save method into the Entity. After that put two types what you need to map. So ItemSaveResponseDTO comes from the frontend. itemSaveResponseDTO is the reference. Always parentheses include the data type comes from the frontend.
//    ItemSaveResponseDTO---->Item
}

// ItemList -----> ItemResponseDTO :- This is the conversion.