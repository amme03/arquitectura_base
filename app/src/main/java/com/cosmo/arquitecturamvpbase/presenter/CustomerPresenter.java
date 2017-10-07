package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ICustomerView;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public class CustomerPresenter extends BasePresenter<ICustomerView> {


        private ICustomerRepository userRepository;

    public CustomerPresenter(ICustomerRepository userRepositori) {

            userRepository = userRepositori;
        }



    public void validateInternetProduct() {


        if (getValidateInternet().isConnected()){

            createThreadUser();

        }else{
            //TODO: implementación alert

        }

    }

    public void createThreadUser() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getUserList();

            }
        });
        thread.start();
    }

    public void getUserList()   {

        try {
            ArrayList<Customer> customerArrayList = userRepository.getUserList();
            getView().showUserList(customerArrayList);


        } catch (RepositoryError repositoryError) {
               getView().showToast(repositoryError.getMessage());
        }
    }












}
