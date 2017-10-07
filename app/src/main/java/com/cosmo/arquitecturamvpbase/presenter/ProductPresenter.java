package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Database;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;

import java.util.ArrayList;

import retrofit.RetrofitError;
import  com.cosmo.arquitecturamvpbase.helper.ShowAlertDialog;
/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductPresenter extends BasePresenter<IProductView> {

    private ProductRepository productRepository;

    public ProductPresenter() {
        productRepository = new ProductRepository();
    }

    public void validateInternetProduct() {


        if (getValidateInternet().isConnected()){

            createThreadProduct();

        }else{
            //TODO: implementación alert
            getView().showAlertDialog(R.string.error,R.string.validate_internet);
        }

    }

    private void createThreadProduct() {
       // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProductList();
                //getProductListDB();
            }
        });
        thread.start();
    }

    private void getProductList() {

        try {
            ArrayList<Product> productArrayList = productRepository.getProductList();
            getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError) {
            //TODO: mostrar alert
        }/*finally {
            getView().hideProgress();
        }*/

    }


    private void getProductListDB() {

        try {
            ArrayList<Product> productArrayList = Database.producDao.fetchAllProdutc();
            getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError){
            //TODO: mostrar alert
        }/*finally {
            getView().hideProgress();
        }*/

    }

    public void getListProduct() {
                if (getValidateInternet().isConnected()) {
                        createThreadProduct();}
                else {
                    getView().showAlertDialog(R.string.error, R.string.validate_internet);
                     }
            }

}
