package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by ana.marrugo on 26/09/2017.
 */

public interface IUpdateProductPresenter extends IBaseView {


    void showProduct(Product product);
}
