package com.springbootacademy.batch7.pos.dto;
import java.util.ArrayList;

public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList<String> contactNumber; // But it is recommended to use List than ArrayList.
    private String nic;
    private boolean active; // This name should be active_state.

    // No args constructor
    public CustomerDTO() {
    }

    // All args constructor
    public CustomerDTO(int customerId, String customerName, String customerAddress, double customerSalary, ArrayList<String> contactNumber, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.active = active;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public ArrayList<String> getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ArrayList<String> contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Override the toString() method.
    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                ", contactNumber=" + contactNumber +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }
}

// Keep variable names ( property names ) the same as entity names ( Customer is entity ) as it are and do not keep two names in the variables.
// This is where data comes to the controller and data comes to the customer DTO. In the frontend, you should create and maintain something like this from these names.
// This is the way how entity is created. If you create an Entity in a name, there must necessarily a DTO in that name with that properties.
// DTO is created to pass the data among the layers.
// The purpose of creating Getters and Setters that we want the DTO for the scenario of passing the DTO data from the controller layer to the service layer. We want Getters and Setters to use that data. Getter for get data.
// The entity is the one that deals with the database from the Service layer to the Repository layer. So if we want to deal with the database, we want the Entity to access the data.
// Both DTO and Entity are a database representation that we should have.