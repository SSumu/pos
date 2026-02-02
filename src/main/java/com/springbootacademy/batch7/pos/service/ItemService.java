package com.springbootacademy.batch7.pos.service;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import jakarta.validation.constraints.Max;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);


    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName);

    List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);

    PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, @Max(50) int size, boolean activeState); // Method in the video had not the part of @Max(50). But @Max(50) part came to here in this Spring Boot version.
}
