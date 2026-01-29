/**
 * File : AddressRepository.java
 * Package : com.clarix.employeemanagement.repository
 * Description : Performs address related database operations
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 * 
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */
package com.clarix.employeemanagement.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.util.HibernateUtil;

/**
 * Performs all operations related to address in the database
 *
 * @see com.clarix.employeemanagement.model
 * @see com.clarix.employeemanagement.util
 */
public class AddressRepository {

    /**
     * Saves the address to the database
     *
     * @param address the address details to be added
     * @return saved address
     */
    public Address saveAddress(Address address) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

             Transaction transaction = session.beginTransaction();
             session.save(address);
             transaction.commit();

             return address;
        }
    }

    /**
     * Retrieves the address based on the id
     * 
     * @param id employee identification number
     * @return the address belongs to the id 
     */
    public Address getAddressById(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
      
            return session.get(Address.class, id);
        }
    }

    /**
     * Updates the address of the employee
     * 
     * @param address the address details to be updated
     */
    public void updateAddress(Address address) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {

            Transaction transaction = session.beginTransaction();
            session.update(address);
            transaction.commit();
        }
    }
}