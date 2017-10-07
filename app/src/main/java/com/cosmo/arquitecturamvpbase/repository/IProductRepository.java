package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.UpdateResponse;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by ana.marrugo on 23/09/2017.
 */

public interface IProductRepository {


    public ArrayList<Product> getProductList() throws RetrofitError;
    public Product onCreateProduct(Product product) throws RetrofitError;
    public DeleteResponse deleteProduct(String id) throws RepositoryError;
    public UpdateResponse updateProduct(String id,Product product) throws RepositoryError;


}
