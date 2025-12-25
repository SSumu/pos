package com.springbootacademy.batch7.pos.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity; // In the video there is javax instead of jakarta.
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;

@Entity // We need to define this as an entity by using this @Entity annotation.
// We can give the name we like to the table as the table name. In this way the table is created with first letter simple in the table name.
@Table(name = "customer") // To change the name of the table. So the table is created with the name customer.
//@TypeDefs({
//        @TypeDef(name = "json",typeClass = JsonType.class)
//}) // To create environment to use JSON. This is additional information. This is the way that video showed how to do it. But @TypeDefs and @TypeDef annotations have been removed from Hibernate. The way how to do this task has been shown below inside the class.
public class Customer {

    // To define the primary key that we have use the annotation of @Id. Without this @ there is an error in the Customer class.
    @Id
    @Column(name = "customer_id",length = 45) // We use @Column annotation to customize the column name as customer_id. Inside the brackets we give name variable, and we give the name of the column which we want the column name to be. We can validate this more. As this is the primary key, this is not null. It must have a value. So this Id cannot exceed numbers more than 45. That is the meaning of this value of the length.
    // Encapsulation. We do an Encapsulation in here.
    private int customerId;

    // We can use @NotBlank instead of nullable. To get the @NotBlank that we have to put the relevant Type or something else but it is easier use nullable.

    @Column(name = "customer_name",length = 100,nullable = false) // So column name in the table is customer_name. Instead of we put not null in the database, we put nullable = false. So customer's name is must be there. If it is not there, there can be data in the table without the customer name.
    private String customerName;

    @Column(name = "customer_address",length = 255)
    private String customerAddress;

    @Column(name = "customer_salary") // @Column is used to change the column name.
    private double customerSalary;

//    @Type(type = "json") // This is in old Hibernate versions. This is not used in the current Hibernate versions.
    @Column(name = "contact_numbers",columnDefinition = "json") // Here the columnDefinition is the definition of the column must be created in the database.
//    private ArrayList contactNumber; // This is the old method in old Hibernate versions. But this is not valid in current Hibernate versions.
    @JdbcTypeCode(SqlTypes.JSON)
    private ArrayList<String> contactNumber = new ArrayList<>(); // As this is an int type only one contact number can be kept there. But one person may have several contact numbers. So to keep several data in one column related to one person we have to use array list. So the way to do this is that we have to save this in MySQL table as a JSON object. So to use JSON that we have to make the background and dependency to use JSON inside this. So anyway there is a list. So even there is a warning in the ArrayList, we have to prepare the background it needs. We have to say this to apply the JSON to this. In here the way shows in the video was deprecated, and it was in old Hibernate versions. This is the method used in the new Hibernate version.
//    Recommended Minor Improvement
//    Although ArrayList<String> works fine, it is better to declare it as the interface type:
//    private List<String> contactNumber = new ArrayList<>();

    @Column(name = "nic")
    private String nic;

    @Column(name =  "active_state",columnDefinition = "TINYINT default 0") // There is a another data type same as boolean which is TINYINT. TINYINT means 1,0 of the int. If something not comes to this, we can send the 1 as the default value.
    private boolean active;

    // This is the constructor. This is a no args constructor means there are no arguments inside this.
//    public Customer(){
//
//    }

    public Customer() {
    }
//    Customer customer = new Customer(); // This is a POJO. Objects are not created in previous way to access things inside a POJO. Previously created object and this object are two things. Creating an object from a POJO means calling the constructor below or above. Because the constructor in the Customer class is a NoArgs constructor means no arguments in it and there is a AllArgs constructor with all the  arguments.

    // This is the all args constructor.
    public Customer(int customerId, String customerName, String customerAddress, double customerSalary, ArrayList<String> contactNumber, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.active = active;
    }

    // This constructor matches to the customer in the Customer class. A constructor is created based on the Id and Name.
    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public ArrayList<String> getContactNumber() {
        return contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public boolean isActive() {
        return active;
    } // We can remove this isActive method if it does not allow us to access in another place.

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public void setContactNumber(ArrayList<String> contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // There is a technology called Lombok to create getters and setters.

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                ", contactNumber=" + contactNumber +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }

    // This is the way how entity is created.
}

// Customer c = new Customer(); // This calls the public Customer(){} no args constructor.
// Customer c = new Customer(

// ); // But if we pass the data to this argument data and put it inside this Customer() constructor, it calls the all args constructor.

// Constructor is called when the object is created from the name of the clas and at the moment of initialization of that object.

// This is an entity. An Entity is a POJO. Entity is an object.
// In addition to this, We need to define entities and repositories for the container.
// An entity must have a primary key which is a unique key.
// There is a standard. Even we create the primary key column as customerId, there it is not created this as like this name. It is like customer_id. If we create like this, it will create this column name as it likes from database. I can customize the name that is generated in this database.

// We can access a field from another place if it is a Getter method.
// This entity is created to represent the table in the database.