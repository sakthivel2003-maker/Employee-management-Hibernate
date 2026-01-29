/*
 * File : AddressController.java
 * Package : com.clarix.employeemanagement.controller
 * Description : Manages storing address details 
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */

package com.clarix.employeemanagement.controller;

import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.service.AddressService;

/**
 * Manages address details like doorNo, street, city and pinCode
 *
 * @see com.clarix.employeemanagement.model
 * @see com.clarix.employeemanagement.service
 */
public class AddressController {
    private AddressService addressService;

    public AddressService getAddressService() {
        if (null == addressService)
            addressService = new AddressService();
        return addressService;       
    }

    /**
     * Adds a new address detail
     * 
     * @param doorNo employee doorNo
     * @param street employee street
     * @param city employee city
     * @param pinCode employee pinCode 
     * @return saved address
     */
    public Address addAddress(String doorNo, String street, String city,
            int pinCode) {

        return getAddressService().addAddress(doorNo, street, city, pinCode);
    }

    /**
     * Updates the address of the employee 
     * 
     * @param addressId address identification number
     * @param doorNo employee doorNo
     * @param street employee street
     * @param city employee city
     * @return saved address
     */
    public boolean updateAddress(int addressId, String doorNo, String street,
            String city, int pinCode) {

        boolean updatedEmployee = getAddressService().updateAddress(addressId,
                doorNo, street, city, pinCode);

        return updatedEmployee;
    }
}