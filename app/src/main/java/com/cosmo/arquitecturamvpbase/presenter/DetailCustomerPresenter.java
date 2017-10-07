package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;

import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;

import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailCustomerView;

import retrofit.RetrofitError;

/**
 * Created by ana.marrugo on 05/10/2017.
 */

public class DetailCustomerPresenter extends BasePresenter<IDetailCustomerView>{

    private ICustomerRepository customerRepository;
    public DetailCustomerPresenter(ICustomerRepository iCustomerRepository){
        this.customerRepository = iCustomerRepository;

    }

    public void createCustomerRepository(Customer customer){
        try{
            Customer customerCreateResponse= customerRepository.onCreateCustomer(customer);

           if(  customerCreateResponse!=null){
                getView().showToast(R.string.corret);
            }else{
                getView().showAlertDialogError(R.string.error);
            }
        } catch (RepositoryError RepositoryError) {
            getView().showToast(RepositoryError.getMessage());

        }
    }

    public void createCustomer(Customer customer) {
        if(getValidateInternet().isConnected()){
            createThreadCreateCustomer( customer);
        }
    }

    public void createThreadCreateCustomer(final Customer customer) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createCustomerRepository( customer);
            }
        });
        thread.start();
    }
}
