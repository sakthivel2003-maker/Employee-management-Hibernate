/*
 * File : EmployeeController.java
 * Package : com.clarix.employeemanagement.controller
 * Description : Manages employee details like add, update, view and delete
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.controller;

import java.time.LocalDate;
import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.model.Employee;	
import com.clarix.employeemanagement.service.EmployeeService;

/**
 * Manages employee details such as adding, updating, viewing and deleting
 *
 * @see com.clarix.employeemanagement.model
 * @see com.clarix.employeemanagement.service
 */
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        if (null == employeeService) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }
    /**
     * Adds a new employee
     * 
     * @param name employee name
     * @param dateOfBirth employee birth date
     * @param address employee address details
     * @param mobile employee mobile number
     * @param salary employee salary
     * @param email employee email id
     * @param pinCode address pincode
     * @return employee or error if not added
     */
    public String addEmployee(String name, LocalDate dateOfBirth, 
            long mobile, double salary, String email, Address currentAddress,
            Address permanentAddress) {
          
        return getEmployeeService().addEmployee(name, dateOfBirth, mobile, 
                salary, email, currentAddress, permanentAddress);
    }

    /**
     * Updates the name of an employee
     *
     * @param id employee identification number
     * @param name employee name
     */
    public void updateName(int id, String name) {
        getEmployeeService().updateName(id, name);
    }

    /**
     * Updates the date of birth of an employee
     *
     * @param id employee identification number
     * @param dateOfBirth employee date of birth
     */
    public void updateBirthDate(int id, LocalDate dateOfBirth) {
        getEmployeeService().updateBirthDate(id, dateOfBirth);
    }

    /**
     * Updates the email of an employee
     *
     * @param id employee identification number
     * @param email employee email
     */
    public void updateEmail(int id, String email) {
        getEmployeeService().updateEmail(id, email);
    }

    /**
     * Updates the phone number of an employee
     *
     * @param id employee identification number
     * @param phone employee phone number
     */
    public void updatePhoneNumber(int id, String phone) {
        getEmployeeService().updatePhoneNumber(id, phone);
    }

    /**
     * Update the salary of the employee
     *
     * @param id employee identification number
     * @param validSalary salary to be validated
     */
    public void updateSalary(int id, String validSalary) {
        getEmployeeService().updateSalary(id, validSalary);
    }

    /**
     * Deletes the employee
     *
     * @param id employee identification number
     * @return status message after deletion
     */
    public String deleteEmployee(int id) {
        return getEmployeeService().deleteEmployee(id);
    }

    /**
     * Display employee information by id
     *
     * @param id employee identification number
     * @return the formatted employee details
     */
    public String viewEmployeeById(int id) {
        return getEmployeeService().viewEmployeeById(id);
    }

    /**
     * Display all the available employee information
     * 
     * @return the formatted employee details
     */
    public String viewAllEmployees() {
        return getEmployeeService().viewAllEmployee();
    }

    /**
     * Display all the available employee information
     * 
     * @param id employee identification number
     * @return the employee 
     */
    public Employee getEmployeeById(int id) {
        return getEmployeeService().getEmployeeById(id);
    }

    /**
     * Validates employee name
     * 
     * @param name employee name
     * @return validation message or null if valid
     */
    public String validateName(String name) {
        return getEmployeeService().validateName(name);
    }   

    /**
     * Validates employee email
     *
     * @param email employee email address
     * @return validation message or null if valid
     */
    public String validateEmail(String email) {
        return getEmployeeService().validateEmail(email);
    }

    /**
     * Validates employee date of birth
     *
     * @param dateOfBirth employee date of birth
     * @return validation message or null if valid
     */
    public String validateDateOfBirth(LocalDate dateOfBirth) {
        return getEmployeeService().validateDateOfBirth(dateOfBirth);
    }
    
    /**
     * Validates employee phoneNumber
     *
     * @param phoneNumber employee phoneNumber
     * @return validation message or null if valid
     */
    public String validatePhoneNumber(String phoneNumber) {
        return getEmployeeService().validatePhoneNumber(phoneNumber);
    }

    /**
     * Validates employee salary
     *
     * @param salary employee salary
     * @return validation message or null if valid
     */
    public String validateSalary(String salary) {
        return getEmployeeService().validateSalary(salary);
    }

}