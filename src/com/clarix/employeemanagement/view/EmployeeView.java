/*
 * File : EmployeeView.java
 * Package : com.clarix.employeemanagement.view
 * Description : Displays menu and perform operations
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import com.clarix.employeemanagement.controller.AddressController;
import com.clarix.employeemanagement.controller.EmployeeController;
import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.model.Employee;

/**
 * Provides options for managing employee information
 *
 * @see com.clarix.employeemanagement.controller.EmployeeController
 * @see com.clarix.employeemanagement.model.Employee
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController;
    private AddressController addressController;

    public EmployeeController getEmployeeController() {       
        if (null == employeeController) 
            employeeController = new EmployeeController();
        return employeeController;
    }

    public AddressController getAddressController() {
        if (null == addressController)
            addressController = new AddressController();
        return addressController;
    }

    /**
     * Displays available options and performs the selected option
     */
    public void executeMenu() {
        boolean exit = false;

        do {
   	    displayMenu();
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> addEmployee();
                
                case 2 -> updateEmployee();
                
                case 3 -> viewEmployeeById();
                
                case 4 -> viewAllEmployees();
                
                case 5 -> deleteEmployee();
    
                case 6 -> updateAddress();
                
                case 7 -> {
                    System.out.println("Are you want to exit? 1.Yes 2.No");
                    exit = (1 == scanner.nextInt());      
                }

                default -> System.out.println("Invalid option");
            }
        } while (!exit);
        
        System.out.println("Application exited...");
    }
    
    /**
     * Displays the available menu options
     */
    public void displayMenu() {
        System.out.println("""
                1.Add Employee
                2.Update Employee
                3.View Employee by id
                4.View All Employees
                5.Delete Employee
                6.Update Address
                7.Exit
                """);
        System.out.print("choose an option: ");
    }

    /**
     * Saves a new employee using provided details
     */
    public void addEmployee() {
        System.out.println("You chose to add employee");
        String name = getValidatedData("NAME", "Enter the name: ");

        LocalDate dateOfBirth = getValidatedBirthDate();
        
        String phone = getValidatedData("PHONENUMBER", 
                "Enter the phoneNumber: ");
        long phoneNumber = Long.parseLong(phone);
        

        String email = getValidatedData("EMAIL", "Enter the email: ");

        String validSalary = getValidatedData("SALARY", "Enter the salary: ");
        double salary = Double.parseDouble(validSalary);

        System.out.println("Enter the current address details");
        System.out.print("Enter the door no: ");
        String currentDoorNo = scanner.nextLine();

        System.out.print("Enter the street: ");
        String currentStreet = scanner.nextLine();

        System.out.print("Enter the city: ");
        String currentCity = scanner.nextLine();

        System.out.print("Enter the pinCode: ");
        int currentPinCode = scanner.nextInt();
        scanner.nextLine();	

        Address currentAddress = getAddressController()
                .addAddress(currentDoorNo, currentStreet, currentCity, 
                currentPinCode);

        System.out.println("Enter the permanent address details");
        System.out.print("Enter the door no: ");
        String permanentDoorNo = scanner.nextLine();

        System.out.print("Enter the street: ");
        String permanentStreet = scanner.nextLine();

        System.out.print("Enter the city: ");
        String permanentCity = scanner.nextLine();

        System.out.print("Enter the pinCode: ");
        int permanentPinCode = scanner.nextInt();
        scanner.nextLine();

        Address permanentAddress = getAddressController()
                .addAddress(permanentDoorNo, permanentStreet, permanentCity,
                permanentPinCode);

        String statusMessage = getEmployeeController().addEmployee(name, 
                dateOfBirth, phoneNumber, salary, email, currentAddress,
                permanentAddress);
        System.out.println(statusMessage); 
    }
    
    /**
     * Modifies existing employee information
     */
    public void updateEmployee() {
        System.out.println("You chose to update employee");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employee = getEmployeeController().getEmployeeById(id);
        if (null == employee) {
            System.out.println("Employee not found");
            return;
        }

        int userChoice;
        do {
            System.out.println("""
                    ---Update Employee menu---
                    1.Update name
                    2.Update DateOfBirth
                    3.Update email
                    4.Update phone Number
                    5.Update salary
                    6.Exit
            """);

            System.out.println("Enter the choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> updateName(id);
              
                case 2 -> updateBirthDate(id);
 
                case 3 -> updateEmail(id);

                case 4 -> updatePhoneNumber(id);

                case 5 -> updateSalary(id);

                case 6 -> System.out.println("Existing from update menu");

                default -> System.out.println("Invalid choice");
            }
        } while (6 != userChoice);
    }

    /**
     * Deletes an employee from the system 
     */
    public void deleteEmployee() {
        System.out.println("You chose to delete employee");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employee = getEmployeeController().getEmployeeById(id);
        if (null == employee) {
            System.out.println("Employee not found");
            return;
        }

        String statusMessage = getEmployeeController().deleteEmployee(id);
        System.out.println(statusMessage);
    }

    /**
     * Display employee information by id
     */
    public void viewEmployeeById() {
        System.out.println("You chose to view employee by id");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
 
        String statusMessage = getEmployeeController().viewEmployeeById(id);
        System.out.println(statusMessage);
    }

   /**
    * Displays all the available employee information
    */   
    public void viewAllEmployees() {
        System.out.println("You chose to view all employees");
        String statusMessage = getEmployeeController().viewAllEmployees();
        System.out.println(statusMessage);
    }

    /**
     * Updates the address of the employee
     */
    public void updateAddress() {
        System.out.println("You chose to update the address");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employee = getEmployeeController().getEmployeeById(id);
        if (null == employee) {
            System.out.println("Employee not found");
            return;
        }

        System.out.println("""
                Which address you want to update?
                1.Current Address
                2.Permanent Address
                """);
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        int addressId = (1 == userChoice)
                ? employee.getCurrentAddress().getId()
                : employee.getPermanentAddress().getId();

        System.out.print("Enter the new doorNo: ");
        String doorNo = scanner.nextLine();

        System.out.print("Enter the new street: ");
        String street = scanner.nextLine();

        System.out.print("Enter the new city: ");
        String city = scanner.nextLine();

        System.out.print("Enter the new pincode: ");
        int pinCode = scanner.nextInt();

        boolean updatedAddress = getAddressController()	
                .updateAddress(addressId, doorNo, street, city, pinCode);

        System.out.println(
                updatedAddress 
                ? "Address updated successfully"
                : "Address not found"
        );
    }

    /**
     * Updates the name of an existing employee
     *  	
     * @param id employee identification number
     */
    public void updateName(int id) {
        String name = getValidatedData("NAME", "Enter new name: ");
        getEmployeeController().updateName(id, name);
        System.out.println("name updated successfully");
    }

    /**
     * Updates the date of birth of an existing employee
     *
     * @param id employee identification number
     */
    public void updateBirthDate(int id) {
        LocalDate dateOfBirth = getValidatedBirthDate();
        getEmployeeController().updateBirthDate(id, dateOfBirth);
        System.out.println("Date of birth updated successfully");
    }

    /**
     * Updates the email of an existing employee
     *
     * @param id employee identification number
     */
    public void updateEmail(int id) {
        String email = getValidatedData("EMAIL", "Enter new email: ");
        getEmployeeController().updateEmail(id, email);
        System.out.println("Email updated successfully");
    }

    /**
     * Updates the phoneNumber of an existing employee
     *
     * @param id employee identification number
     */
    public void updatePhoneNumber(int id) {
        String phoneNumber = getValidatedData("PHONENUMBER", "Enter number: ");
        getEmployeeController().updatePhoneNumber(id, phoneNumber);
        System.out.println("Mobile number updated successfully");
    }

    /**
     * Updates the salary of an existing employee
     *
     * @param id employee identification number
     */
    public void updateSalary(int id) {
        String salary = getValidatedData("SALARY", "Enter salary: ");
        getEmployeeController().updateSalary(id, salary);
        System.out.println("salary updated successfully");
    }

    /**
     * Validates the given details
     *
     * @param validationType type of the details
     * @prompt message to fill the detail
     * @return valid detail or error message if invalid format
     */
    public String getValidatedData(String validationType, String prompt) {
        while (true) {
            System.out.print(prompt);
            String userData = scanner.nextLine();

            String validationMessage = switch (validationType) {
                case "NAME" -> getEmployeeController().validateName(userData);

                case "EMAIL" -> getEmployeeController()
                        .validateEmail(userData);

                case "PHONENUMBER" -> getEmployeeController()
                        .validatePhoneNumber(userData);

                case "SALARY" -> getEmployeeController()
                        .validateSalary(userData);

                default -> "Invalid validation type";
                            
            };

            if (null == validationMessage) 
                return userData;

            System.out.println(validationMessage);
        }
    }
    /**
     * Validates employee birth date
     *
     * @return a birth date that is valid
     */
    public LocalDate getValidatedBirthDate() {
    
        while (true) {
            try {
                System.out.print("Enter the date of birth(dd/MM/yyyy): ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                String validationMessage = getEmployeeController()
                        .validateDateOfBirth(dateOfBirth);
                if (null == validationMessage) 
                    return dateOfBirth;

                System.out.println(validationMessage);
            } catch (DateTimeParseException invalidDateException) {
                System.out.println("Invalid date format. Use dd/MM/yyyy");
            }
        }
    }
}