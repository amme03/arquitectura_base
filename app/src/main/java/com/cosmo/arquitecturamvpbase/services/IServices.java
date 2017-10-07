package com.cosmo.arquitecturamvpbase.services;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.UpdateResponse;
import com.cosmo.arquitecturamvpbase.model.Customer;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {


    @GET("/products")
    ArrayList<Product> getProductList();

    @POST("/products")
    Product onCreateProduct(@Body Product product);;

    @DELETE("/products/{id}")
    DeleteResponse onDelete(@Path("id") String id);

    @PUT("/products/{id}")
    UpdateResponse onUpdate(@Path("id") String id, @Body Product product);

    @GET("/customers")
    ArrayList<Customer> getCustomersList();

    @POST("/customers")
    Customer addCustomer(@Body Customer customer);

}
