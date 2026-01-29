/*
 * File : Address.java
 * Package : com.clarix.employeemanagement.model
 * Description : stores address related information
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Address {

    private int id;
    private int pinCode;
    private String city;
    private String doorNo;
    private String street;
    private List<Employee> employees = new ArrayList<>();

    public Address() {}

    public Address(int pinCode, String city, String doorNo, String street) {
        this.pinCode = pinCode;
        this.city = city;
        this.doorNo = doorNo;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Door No: ").append(doorNo).append("\n")
                .append("Street: ").append(street).append("\n")
                .append("City: ").append(city).append("\n")
                .append("Pincode: ").append(pinCode)
                .toString();
    }
}