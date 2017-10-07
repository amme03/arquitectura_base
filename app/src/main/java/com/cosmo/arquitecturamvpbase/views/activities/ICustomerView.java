package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public interface ICustomerView extends IBaseView {


    void showUserList(ArrayList<Customer> customerArrayList);
    void showAlertDialog(int mensaje);
    void showToast(int mensaje);
    void showToast(String mensaje);
}
