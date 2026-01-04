package com.springbootacademy.batch7.pos.entity;

import com.springbootacademy.batch7.pos.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity // Entity is a table representation.
@Table(name = "item") // Name of the table. name is the variable.
@NoArgsConstructor // These annotations come with the LomBok annotation.
public class Item { // The error in the Item with empty {} means it needs a primary key.
    @Id
    @Column(name = "item_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type",length = 100,nullable = false)
//    private String measuringUnitType; // But it is a problem if we give measuring types in several ways. We can only define a few values that can be put into measuringUnitType.
    private MeasuringUnitType measuringUnitType; // So we have to create a separate enum with this field name measuringUnitType. Here we create a class named MeasuringUnitType but just for now it is not created yet.
//   After the MeasuringUnitType enum class was created, we can import MeasuringUnitType enum class from here. Press Alt + Enter if the import will not be shown when the cursor is hovered over the class. If this is an enumeration class, we must define it as @Enumerated. We must give the EnumType inside the brackets which is EnumType.STRING. EnumType.STRING means there is String values inside there. Now we have to define the types of the values inside the MeasuringUnitType enum class. If the data are sent to this from frontend except from the defined types inside the enum class, then it gives an error.

    @Column(name = "balance_qty",length = 100,nullable = false)
    private double balanceQty; // balanceQty means quantity.

    @Column(name = "supplier_price",length = 100,nullable = false)
    private double supplierPrice; // balanceQty means quantity.

    @Column(name = "selling_price",length = 100,nullable = false)
    private double sellingPrice;

    @Column(name =  "active_state",columnDefinition = "TINYINT default 0") // There is an another data type same as boolean which is TINYINT. TINYINT means 1,0 of the int. If something not comes to this, we can send the 1 as the default value.
    private boolean activeState;

    // Here we do not create constructor for this. To do this thing Spring introduce us the Lombok dependency.
    // There cannot be two methods with the same name. There can be two methods with different parameters which is called overloading.

    // NoArgs constructor means that it has no parameters.
//    public Item() {
//    }
}

// Item entity to create a table for storing item data of the customers.
// enum is a data type which it has defined the values can be assigned to it. If values comes to this outside of these defined values, it does not allow those values to it. enum is used to define a fixed set of constants.
// Here we have to create another package inside the entity package, but we can create that class inside the entity package if we want. But it is easy if we create another package for that.
// Instead of that
