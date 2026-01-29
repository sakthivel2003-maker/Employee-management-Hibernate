/*
 * File : EmployeeService.java
 * Package : com.clarix.employeemanagement.service
 * Description :  Manages employee details and related rules
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import com.clarix.employeemanagement.model.Employee;
import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.repository.EmployeeRepository;

/**
 * Manages employee information and related validations
 * 
 * @see com.clarix.employeemanagement.model.Employee
 */
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeRepository getEmployeeRepository() {
        if (null == employeeRepository) {
            employeeRepository = new EmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Adds a new employee
     *
     * @param name employee name
     * @param dateOfBirth employee birth date
     * @param address employee address details
     * @param phoneNumber employe mobile number
     * @param salary employee salary
     * @param email employee email id
     * @param pinCode address pincode
     * @return status message after adding the employee
     */
    public String addEmployee(String name, LocalDate dateOfBirth,
            long phoneNumber, double salary, String email, 
            Address currentAddress, Address permanentAddress) {
     
        int age = calculateAge(dateOfBirth);
        
        Employee employee = new Employee();
        employee.setName(name);
        employee.setDateOfBirth(dateOfBirth);
        employee.setPhoneNumber(phoneNumber);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setAge(age);
        employee.setActiveStatus(true);
        employee.setCurrentAddress(currentAddress);
        employee.setPermanentAddress(permanentAddress);

        Employee savedEmployee = getEmployeeRepository().addEmployee(employee);
        return String.format("Employee saved successfully: %s", savedEmployee);                        
    }	

    /**
     * Update employee name
     *
     * @param id employee identification number
     * @param name employee name
     */
    public void updateName(int id, String name) {
        Employee employee = getEmployeeRepository().getEmployeeById(id);
        if (null != employee) {
            employee.setName(name);
            getEmployeeRepository().updateEmployee(employee);
        }
    }

    /**
     * Update employee date of birth
     * 
     * @param id employee identification number
     * @param dateOfBirth employee date of birth
     */
    public void updateBirthDate(int id, LocalDate dateOfBirth) {
        Employee employee = getEmployeeRepository().getEmployeeById(id);
        if (null != employee) {
            employee.setDateOfBirth(dateOfBirth);
            getEmployeeRepository().updateEmployee(employee);
        }
    }

    /**
     * Update employee email
     *
     * @param id employee identification number
     * @param email employee email 
     */
    public void updateEmail(int id, String email) {
        Employee employee = getEmployeeRepository().getEmployeeById(id);
        if (null != employee) {
            employee.setEmail(email);
            getEmployeeRepository().updateEmployee(employee);
        }
    }

    /**
     * Update employee phone number
     * 
     * @param id employee identification number
     * @param phone employee phone number
     */
    public void updatePhoneNumber(int id, String phone) {
        Employee employee = getEmployeeRepository().getEmployeeById(id);
        Long phoneNumber = Long.parseLong(phone);
        if (null != employee) {
            employee.setPhoneNumber(phoneNumber);
            getEmployeeRepository().updateEmployee(employee);
        }
    }

    /**
     * Update employee salary
     * 
     * @param id employee identification number
     * @param validSalary salary to be validated
     */
    public void updateSalary(int id, String validSalary) {
        Employee employee = getEmployeeRepository().getEmployeeById(id);
        Double salary = Double.parseDouble(validSalary);
        if (null != employee) {
            employee.setSalary(salary);
            getEmployeeRepository().updateEmployee(employee);
        }
    }

    /**
     * Deletes the employee
     *
     * @param id employee identification number
     * @return status message after deletion
     */
    public String deleteEmployee(int id) {
        boolean deletedEmployee = getEmployeeRepository().deleteEmployee(id);

        return deletedEmployee ? "Employee deleted successfully"
                : "Employee not found";
    }

    /**
     * Displays the employee information by id
     *
     * @param id employee identification number
     * @return the formatted employee details
     */
    public String viewEmployeeById(int id) {
        Employee employee = getEmployeeRepository().viewEmployeeById(id);
        return formatEmployee(employee);
    }

    /**
     * Displays all the available employee information
     *
     * @return the formatted employee details
     */
    public String viewAllEmployee() {
        List<Employee> employees = getEmployeeRepository().viewAllEmployees();
        StringBuilder builder = new StringBuilder();
 
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                builder.append(formatEmployee(employee)).append("\n");
            }
        } else {
            builder.append("No employees found");
        }

        return builder.toString();
    }

    /**
     * Validates employee name
     *
     * @param name employee name
     * @return valildation message or null if valid
     */
    public String validateName(String name) {
        return name.matches("^[A-Za-z ]{3,30}$") ? null 
                : "Invalid name (3-30 letters only)";
    }

    /**
     * Validates employee date of birth
     *
     * @param dateOfBirth employee date of birth
     * @return validation message or null if valid
     */
    public String validateDateOfBirth(LocalDate dateOfBirth) {
        int age = calculateAge(dateOfBirth);
        return (age >= 18 && age <= 60) ? null : "Employee age must be 18-60";
    }
    
    /**
     * Validates employee email
     * 
     * @param email employee email address
     * @return validation message or null if valid
     */
    public String validateEmail(String email) {
        return !email.matches("^[A-Za-z0-9_.]+@[A-Za-z0-9.]+$")
                ? "Invalid Email format" 
                : getEmployeeRepository().isEmailExist(email)
                ? "Email already exist"
                : null;
    }

    /**
     * Validates employee phone number
     *
     * @param phoneNumber employee phone number
     * @return validation message or null if valid
     */
    public String validatePhoneNumber(String phoneNumber) {
        return !phoneNumber.matches("^[6-9][0-9]{9}$")
                ? "Invalid mobile format"
                : getEmployeeRepository()
                    .isPhoneNumberExist(Long.parseLong(phoneNumber))
                ? "Phone number already exist"
                : null;
    }

    /**
     * Validates employee salary
     *
     * @param salary employee salary
     * @return validation message or null if valid
     */
    public String validateSalary(String salary) {
        return !salary.matches("^[0-9]{1,6}(\\.[0-9]{1,2})?$")
                ? "salary upto 6 digits only"
                : null;
    }

    /**
     * Checks the employee with the id is present
     * 
     * @param id employee identification number 
     * @return the employee belongs to that id
     */
    public Employee getEmployeeById(int id) {
        return getEmployeeRepository().getEmployeeById(id);
    }

    /**
     * calculate employee age based on the date of birth
     *
     * @param dateOfBirth employee date of birth
     * @return age of the employee
     */
    public int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Formats employee details for display
     *
     * @param employee details of one employee
     * @return formatted employee information 
     */
    public String formatEmployee(Employee employee) {
        
        if (null == employee) 
            return "Employee not found";

        return String.format("""
                Id: %d	Name: %s Dob: %s Age: %d Email: %s Phone: %d
                Salary: %.2f Active: %b Current Address: %s
                Permanent Address: %s
                """, employee.getId(), employee.getName(), 
                employee.getDateOfBirth(), employee.getAge(), 
                employee.getEmail(), employee.getPhoneNumber(),
                employee.getSalary(), employee.getActiveStatus(),
                employee.getCurrentAddress(), employee.getPermanentAddress()
      
        );
    }
}