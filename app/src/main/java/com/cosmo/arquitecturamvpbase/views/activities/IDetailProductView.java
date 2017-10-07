package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by ana.marrugo on 23/09/2017.
 */

public interface IDetailProductView extends IBaseView {

  void showAlertDialog(int validate_internet);
  void showAlertDialogError(int validate_internet);
  void showToast(int validate_internet);
  void showToast(String validate_internet);


}
