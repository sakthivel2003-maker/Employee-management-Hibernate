/*
 * File : EmployeeManagement.java
 * Description : Stores and manages employee information
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

import com.clarix.employeemanagement.view.EmployeeView;

/**
 * Manages employee details including adding, updating, viewing and deleting
 */
public class EmployeeManagement {
    public static void main(String[] args) {
        
        EmployeeView employeeView = new EmployeeView();
        employeeView.executeMenu();
    }
}