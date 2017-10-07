package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by Ana Maria Marrugo on 16/09/17.
 */

public interface ICreateCustomerView extends IBaseView {


    void  showCustomer(Customer customer);
}
