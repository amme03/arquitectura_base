package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface ICreateProductView extends IBaseView {


    void showProduct(Product product);
}
