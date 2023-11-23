package com.yaps.petstore.server.service.customer;

import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.service.AbstractRemoteService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is a session facade for all customer services.
 */
public class CustomerServiceBean extends AbstractRemoteService {

    // ======================================
    // =            Constructors            =
    // ======================================
    public CustomerServiceBean() {
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public CustomerDTO authenticate(final String customerId, final String password) throws FinderException, CheckException {
        final String mname = "authenticate";
        Trace.entering(getCname(), mname, new Object[]{customerId, password});

        if (customerId == null || "".equals(customerId))
            throw new CheckException("Invalid id");
        if (password == null || "".equals(password))
            throw new CheckException("Invalid password");

        final Customer customer = new Customer();

        // Finds the object
        customer.findByPrimaryKey(customerId);

        // Check if it's the right password, if not, a CheckException is thrown
        customer.matchPassword(password);

        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        Trace.exiting(getCname(), mname, customerDTO);
        return customerDTO;
    }

    public CustomerDTO createCustomer(final CustomerDTO customerDTO) throws CreateException, CheckException {
        final String mname = "createCustomer";
        Trace.entering(getCname(), mname, customerDTO);

        if (customerDTO == null)
            throw new CheckException("Customer object is null");

        // Transforms DTO into domain object
        final Customer customer = new Customer(customerDTO.getId(), customerDTO.getFirstname(), customerDTO.getLastname());
        customer.setPassword(customerDTO.getPassword());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setEmail(customerDTO.getEmail());

        customer.setCity(customerDTO.getCity());
        customer.setCountry(customerDTO.getCountry());
        customer.setState(customerDTO.getState());
        customer.setStreet1(customerDTO.getStreet1());
        customer.setStreet2(customerDTO.getStreet2());
        customer.setZipcode(customerDTO.getZipcode());

        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
        customer.setCreditCardType(customerDTO.getCreditCardType());

        // Creates the object
        customer.create();

        // Transforms domain object into DTO
        final CustomerDTO result = transformCustomer2DTO(customer);

        Trace.exiting(getCname(), mname, result);
        return result;
    }

    public CustomerDTO findCustomer(final String customerId) throws FinderException, CheckException {
        final String mname = "findCustomer";
        Trace.entering(getCname(), mname, customerId);

        if (customerId == null || "".equals(customerId))
            throw new CheckException("Invalid id");

        final Customer customer = new Customer();

        // Finds the object
        customer.findByPrimaryKey(customerId);

        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        Trace.exiting(getCname(), mname, customerDTO);
        return customerDTO;
    }

    public void deleteCustomer(final String customerId) throws RemoveException, CheckException {
        final String mname = "deleteCustomer";
        Trace.entering(getCname(), mname, customerId);

        if (customerId == null || "".equals(customerId))
            throw new CheckException("Invalid id");

        final Customer customer = new Customer();

        // Checks if the object exists
        try {
            customer.findByPrimaryKey(customerId);
        } catch (FinderException e) {
            throw new CheckException("Customer must exist to be deleted");
        }

        // Deletes the object
        customer.remove();
    }

    public void updateCustomer(final CustomerDTO customerDTO) throws UpdateException, CheckException {
        final String mname = "updateCustomer";
        Trace.entering(getCname(), mname, customerDTO);

        if (customerDTO == null)
            throw new CheckException("Customer object is null");
        if (customerDTO.getId() == null || "".equals(customerDTO.getId()))
            throw new CheckException("Invalid id");

        Customer customer = new Customer();

        // Checks if the object exists
        try {
            customer.findByPrimaryKey(customerDTO.getId());
        } catch (FinderException e) {
            throw new CheckException("Customer must exist to be updated");
        }

        // Transforms DTO into domain object
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setPassword(customerDTO.getPassword());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setEmail(customerDTO.getEmail());

        customer.setCity(customerDTO.getCity());
        customer.setCountry(customerDTO.getCountry());
        customer.setState(customerDTO.getState());
        customer.setStreet1(customerDTO.getStreet1());
        customer.setStreet2(customerDTO.getStreet2());
        customer.setZipcode(customerDTO.getZipcode());

        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
        customer.setCreditCardType(customerDTO.getCreditCardType());

        // Updates the object
        customer.update();
    }

    public Collection findCustomers() throws FinderException {
        final String mname = "findCustomers";
        Trace.entering(getCname(), mname);

        // Finds all the objects
        final Collection customers = new Customer().findAll();

        // Transforms domain objects into DTOs
        final Collection customersDTO = transformCustomers2DTOs(customers);

        Trace.exiting(getCname(), mname, new Integer(customersDTO.size()));
        return customersDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private CustomerDTO transformCustomer2DTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setCity(customer.getCity());
        customerDTO.setCountry(customer.getCountry());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setState(customer.getState());
        customerDTO.setStreet1(customer.getStreet1());
        customerDTO.setStreet2(customer.getStreet2());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setZipcode(customer.getZipcode());
        customerDTO.setCreditCardNumber(customer.getCreditCardNumber());
        customerDTO.setCreditCardType(customer.getCreditCardType());
        customerDTO.setCreditCardExpiryDate(customer.getCreditCardExpiryDate());
        return customerDTO;
    }

    private Collection transformCustomers2DTOs(final Collection customers) {
        final Collection customersDTO = new ArrayList();
        for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
            final Customer customer = (Customer) iterator.next();
            customersDTO.add(transformCustomer2DTO(customer));
        }
        return customersDTO;
    }
}
