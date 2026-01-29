/*
 * File : AddressService.java
 * Package : com.clarix.employeemanagement.service
 * Description :  Manages address details and related rules
 * Author : sakthi
 * Email : sakthi@gmail.com
 * Created on : 27-01-2026
 * Version : 1.0.0
 *
 * Copyright : © 2026 clarivium technologies. All rights reserved
 */
package com.clarix.employeemanagement.service;

import com.clarix.employeemanagement.model.Address;
import com.clarix.employeemanagement.repository.AddressRepository;

/**
 * Manages address information and related validations
 *
 * @see com.clarix.employeemanagement.model
 * @see com.clarix.employeemanagement.repository
 */
public class AddressService {
    private AddressRepository addressRepository;

    public AddressRepository getAddressRepository() {
        if (null == addressRepository) {
            addressRepository = new AddressRepository();
        }
        return addressRepository;
    }

    /**
     * Add a new address detail
     *
     * @param doorNo employee doorNo
     * @param street employee street
     * @param city employee city
     * @param pinCode employee pincode
     * @return saved address
     */
    public Address addAddress(String doorNo, String street, String city, 
            int pinCode) {

        Address address = new Address();
        address.setDoorNo(doorNo);
        address.setStreet(street);
        address.setCity(city);
        address.setPinCode(pinCode);

        Address savedAddress = getAddressRepository().saveAddress(address);
        return savedAddress;        
    }

    /**
     * Updates the address of the employee
     * 
     * @param addressId identification number of the address
     * @param doorNo employee doorNo
     * @param street employee street
     * @param city employee city 
     * @param pinCode employee pincode
     * @return true if address exist and false otherwise
     */
    public boolean updateAddress(int addressId, String doorNo, String street,
            String city, int pinCode) {

        Address address = getAddressRepository().getAddressById(addressId);
        boolean updatedEmployee = (null != address);
     
        if (updatedEmployee) {
            address.setDoorNo(doorNo);
            address.setStreet(street);
            address.setCity(city);
            address.setPinCode(pinCode);

            getAddressRepository().updateAddress(address);
        }

        return updatedEmployee;
    }
}