package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.Customer;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public interface ICustomerRepository {

    public ArrayList<Customer> getUserList() throws RepositoryError;
    public Customer onCreateCustomer(Customer customer) throws RepositoryError;
}
