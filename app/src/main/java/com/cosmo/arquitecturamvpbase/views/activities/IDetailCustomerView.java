package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public interface IDetailCustomerView extends IBaseView {

    void showAlertDialog(int validate_internet);
    void showAlertDialogError(int validate_internet);
    void showPhoneList(final ArrayList<Phone> phoneArrayList);
    void showToast(int validate_internet);
    void showToast(String validate_internet);
}
