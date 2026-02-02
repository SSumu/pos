package com.springbootacademy.batch7.pos.service;

import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
