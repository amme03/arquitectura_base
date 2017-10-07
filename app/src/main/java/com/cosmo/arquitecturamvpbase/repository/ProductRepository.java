package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.UpdateResponse;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductRepository implements IProductRepository {

    private IServices services;


    public ProductRepository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        services = (IServices) servicesFactory.getInstance(IServices.class);

    }

    public ArrayList<Product> getProductList() throws RetrofitError{

        ArrayList<Product>  products = services.getProductList();
        return products;

    }

    @Override
    public Product onCreateProduct(Product product) throws RetrofitError{

    return  services.onCreateProduct(product);
    }

    @Override
    public DeleteResponse deleteProduct(String id) throws RepositoryError {
        try {
            return services.onDelete(id);
        }catch (RetrofitError retrofitError)
        {
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
        //DeleteResponse deleteResponse = new DeleteResponse();

    }

    @Override
    public UpdateResponse updateProduct(String id,Product product) throws RepositoryError {
        try {
           UpdateResponse updateResponse = services.onUpdate(id,product);
            updateResponse.setStatus(true);
            return updateResponse;
        }catch (RetrofitError retrofitError)
        {
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }


    }
}
