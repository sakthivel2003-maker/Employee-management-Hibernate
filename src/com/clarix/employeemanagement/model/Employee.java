/*
 * File : Employee.java
 * Package : com.clarix.employeemanagement.model
 * Description : stores personal, contact and salary related information
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.model;

import java.time.LocalDate;

/**
 * stores personal, contact and salary related information
 */
public class Employee {

    private boolean activeStatus;
    private int age;
    private int id;
    private int pinCode;
    private long phoneNumber;
    private double salary;
    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private Address currentAddress;
    private Address permanentAddress;

    public Employee() {}
    
    public Employee(String name, LocalDate dateOfBirth, long phoneNumber, 
            double salary, String email, int pinCode, 
            Address currentAddress, Address permanentAddress) {
        
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.email = email;
        this.pinCode = pinCode;    
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return activeStatus;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Id: ").append(id).append("\n")
                .append("Name: ").append(name).append("\n")
                .append("DOB: ").append(dateOfBirth).append("\n")
                .append("Age: ").append(age).append("\n")
                .append("Phone number: ").append(phoneNumber).append("\n")
                .append("Salary: ").append(salary).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Active: ").append(activeStatus).append("\n")
                .append("current Address: \n").append(null != currentAddress
                        ? currentAddress : "Not available").append("\n")
                .append("permanent Address:\n")
                .append(null != permanentAddress ? permanentAddress
                        : "Not available").append("\n")
                .toString();	
    }
}