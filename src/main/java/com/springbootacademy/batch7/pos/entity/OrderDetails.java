package com.springbootacademy.batch7.pos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
//@TypeDefs({
//        @TypeDef(name = "json",typeClass = JsonType.class)
//}) // To create environment to use JSON. This is additional information. This is the way that video showed how to do it. But @TypeDefs and @TypeDef annotations have been removed from Hibernate. The way how to do this task has been shown below inside the class.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    @Id
    @Column(name = "order_details_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId; // orderDetailsId is not repeated.

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Column(name = "qty",length = 100,nullable = false)
    private double qty;

    @Column(name = "amount",nullable = false) // New entity. nullable = false means this amount definitely has a value otherwise the order is useless.
    private Double amount;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    @JsonBackReference // This annotation was not in the video but current Spring Boot versions need it. This is required to display orders in Swagger.
    private Order orders; // Here it is orders because we put the orders there. It is not order. order_id is repeated in the OrderDetails. orders requires an Order object to save.

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items; // Here it is items because we put the items there. It is not item. items requires an Item object to save.


}
