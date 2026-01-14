package com.springbootacademy.batch7.pos.dto.request;

import com.springbootacademy.batch7.pos.entity.enums.MeasuringUnitType;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSaveRequestDTO {

    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty; // balanceQty means quantity.
    private double supplierPrice;
    private double sellingPrice;

}
