package com.springbootacademy.batch7.pos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
//@TypeDefs({
//        @TypeDef(name = "json",typeClass = JsonType.class)
//}) // To create environment to use JSON. This is additional information. This is the way that video showed how to do it. But @TypeDefs and @TypeDef annotations have been removed from Hibernate. The way how to do this task has been shown below inside the class.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @Column(name = "order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

//  This active_state column has been added because the video has this column in the orders table.
    @Column(name =  "active_state",columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false) // So JoinColumn is placed where the field is created.
//    private Customer customer; // Here we map the Customer to the Order. customer_id is the name of the column. This customer is the reference which must be the name we put to the mappedBy in the orders field in the Customer class. Otherwise, if we put customers here, then we must put that customers to the mappedBy in the orders field in the Customer class. (e.g.:- mappedBy="customer" in the orders field in the Customer class)
    private Customer customers; // Here we put the object to the Customer class. orderId is an int type. But in the database, customer_id is an int type field. That is the type of the primary key in the Customer table which is int type of the customerId.

//    So Customer's field is created inside the Order.
//    So in the Order, CustomerId field is created related to the order.

    // It needs to map the Order table and Customer table. To map these two tables, definitely there must be a customerId in this order table.
//    private String customerName; // But we do not write like this. We need to map this. This is a mapping. This is a relationship. This a One-to-Many relationship between two tables. One-to-Many means one customer has many orders. But one order has only one customer.

//    This side is ManyToOne.
//    So we can say Order table and Customer table was correctly mapped.So there is nothing created in the Customer table(entity). Because in the Customer table, it cannot display multiple order IDs.
//    So this relationship is mapped from the orders side which is many side.
//    There is a customer related to the order.
//    If there is a second order, there is a customer related to that second order.

    @Column(name = "order_date",columnDefinition = "DATETIME") // New entity
    private Date date;

    @Column(name = "total",nullable = false) // New entity. nullable = false means this total definitely has a value otherwise the order is useless.
    private Double total;

//  One order has many Order Details. So this is OneToMany relationship.
    @OneToMany(mappedBy="orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // This annotation was not in the video but current Spring Boot versions need it. This is required to display orders in Swagger.
    private Set<OrderDetails> orderDetails = new HashSet<>(); // Here it is orderDetails because we put the orderDetails there. It is not orderDetail.
// There is a list named OrderDetails in this Order.
//  It was needed to use cascade = CascadeType.ALL inside the @OneToMany() and it was not in the video. This is required to display orders in Swagger.
        //    This causes:
        // Infinite JSON loop
        // Swagger hides or ignores fields
        // Or returns empty arrays

    public Order(Customer customers, Date date, Double total) {
        this.customers = customers;
        this.date = date;
        this.total = total;
    }
//    customers is an object.
}
