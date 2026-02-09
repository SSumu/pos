package com.springbootacademy.batch7.pos.service;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import jakarta.validation.constraints.Max;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, @Max(50) int size);
}
