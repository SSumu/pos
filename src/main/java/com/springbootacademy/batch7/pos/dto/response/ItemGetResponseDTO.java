package com.springbootacademy.batch7.pos.dto.response;

import com.springbootacademy.batch7.pos.entity.enums.MeasuringUnitType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemGetResponseDTO {
    private int itemId;
    private String itemName;
    private double balanceQty; // balanceQty means quantity.
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;
}

// Do not keep the @Column above the fields. MeasuringUnitType is not needed as we do not put it there.
