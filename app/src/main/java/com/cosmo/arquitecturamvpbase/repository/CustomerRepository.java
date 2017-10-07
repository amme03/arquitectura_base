package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public class CustomerRepository implements ICustomerRepository {

    private IServices services;


    public CustomerRepository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        services = (IServices) servicesFactory.getInstance(IServices.class);

    }

    @Override
    public ArrayList<Customer> getUserList() throws RetrofitError {
        ArrayList<Customer> customers =services.getCustomersList();

        return customers;
    }

    @Override
    public Customer onCreateCustomer(Customer customer) throws RepositoryError {

        customer=services.addCustomer(customer);
        return customer;
    }
}
