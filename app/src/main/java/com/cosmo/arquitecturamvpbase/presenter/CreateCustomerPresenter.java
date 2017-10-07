package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateCustomerView;

import retrofit.RetrofitError;

/**
 * Created by Ana Maria Marrugo on 06/10/17.
 */

public class CreateCustomerPresenter extends BasePresenter<ICreateCustomerView> {

    private CustomerRepository customerRepository;

    public CreateCustomerPresenter(ICustomerRepository customerRepository) {
        this.customerRepository = (CustomerRepository) customerRepository;
    }

    public void validateInternetCustomer(Customer customer) {


        if (getValidateInternet().isConnected()) {

            createThreadCreateCustomer(customer);

        } else {
            //TODO: implementación alert
        }

    }

    public void createNewCustomer(Customer customer) {

        if (getValidateInternet().isConnected()) {
            createThreadCreateCustomer(customer);
            getView().showCustomer(customer);
        } else {
            getView().showProgress(R.string.error);
        }
    }


    public void createThreadCreateCustomer(final Customer customer) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createNewCustomerService(customer);

            }
        });
        thread.start();
    }



    private void createNewCustomerService(Customer customer) {
        try {
            customerRepository.onCreateCustomer(customer);
            getView().showProgress(customer.getName());
        } catch (RepositoryError repositoryError) {
            getView().showProgress("ERROR");
        }
    }




}
