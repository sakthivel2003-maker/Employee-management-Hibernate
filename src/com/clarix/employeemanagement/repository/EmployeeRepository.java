/**
 * File : EmployeeRepository.java
 * Package : com.clarix.employeemanagement.repository
 * Description : Performs employee related database operations
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 * 
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */
package com.clarix.employeemanagement.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.clarix.employeemanagement.model.Employee;
import com.clarix.employeemanagement.util.HibernateUtil;

/**
 * Performs all operations related to employee data in the database
 *
 * @see com.clarix.employeemanagement.model.Employee
 */
public class EmployeeRepository {

    /**
     * Adds a new employee to the database
     * 
     * @param employee the information of the employee to save
     * @return the employee with its id 
     */
    public Employee addEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();

            return employee; 
        }
    }

    /**
     * Retrieves employee by id
     *  
     * @param id employee identification number
     * @return employee object or null if not found
     */
    public Employee getEmployeeById(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
 
            return session.get(Employee.class, id);
        }
    }

    /**
     * Updates employee details in the database
     * 
     * @param employee the entity to be updated
     */
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();  
        }
    }

    /**
     * Deletes an employee
     *
     * @param id employee identification number	
     * @return true if employee added, false otherwise
     */
    public boolean deleteEmployee(int id) {
        String query = """
            update Employee set activeStatus = false
            where id = :id and activeStatus = true
            """;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            int deletedEmployee = session.createQuery(query)
                    .setParameter("id", id).executeUpdate();
            transaction.commit();
            return deletedEmployee > 0;
        }
    }

    /**
     * Retrieves an active employee along with the address details
     * 
     * @param id employee identification number
     * @return employee with address information
     */
    public Employee viewEmployeeById(int id) {
        String query = """
                from Employee e 
                join fetch e.currentAddress
                join fetch e.permanentAddress
                where e.id = :id and e.activeStatus = true
                """;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            return session.createQuery(query, Employee.class)
                    .setParameter("id", id).uniqueResult();
        }
    }

    /**
     * Retrieves all active employees with address details
     *
     * @return list of employees
     */
    public List<Employee> viewAllEmployees() {
        String query = """
                from Employee e 
                join fetch e.currentAddress
                join fetch e.permanentAddress
                where e.activeStatus = true
                """;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            return session.createQuery(query, Employee.class).list();
        }
    }

    /**
     * Checks if an email already exists in the database
     *
     * @param email the email to check
     * @return true if email exists, false otherwise
     */
    public boolean isEmailExist(String email) {
    
        String query = """
                select count(e.id) from Employee e where e.email = :email
                """;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Long employee = (Long) session.createQuery(query)
                    .setParameter("email", email).uniqueResult();

            return null != employee && employee > 0;
        }          
    } 

    /**
     * Checks if a phoneNumber already exists in the database
     * 
     * @param phoneNumber the employee phone number
     * @return true if phone number exists, false otherwise
     */
    public boolean isPhoneNumberExist(long phoneNumber) {
    
        String query = """
                select count(e.id) from Employee e 
                where e.phoneNumber = :phoneNumber
                """;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Long employee = (Long) session.createQuery(query)
                    .setParameter("phoneNumber", phoneNumber).uniqueResult();

            return null != employee && employee > 0;
        }
    }
} 