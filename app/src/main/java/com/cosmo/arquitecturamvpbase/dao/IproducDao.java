package com.cosmo.arquitecturamvpbase.dao;

import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 30/09/2017.
 */

public interface IproducDao {
    public ArrayList <Product> fetchAllProdutc();
    public Boolean createProduct(Product product);
}
