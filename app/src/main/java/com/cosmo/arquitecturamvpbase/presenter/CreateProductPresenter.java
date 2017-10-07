package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.helper.Database;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateProductView;


import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class CreateProductPresenter extends BasePresenter<ICreateProductView> {

    private ProductRepository productRepository;

    public CreateProductPresenter(IProductRepository productRepository){
        this.productRepository = (ProductRepository) productRepository;
    }

    public void validateInternetProduct(Product product) {


        if (getValidateInternet().isConnected()){

            createThreadCreateProduct(product);

        }else{
            //TODO: implementación alert
        }

    }

    public void createNewProduct(String name, String description, String price, String quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
           // getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }


    public void createThreadCreateProduct(final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //createNewProductService(product);
                createNewProductBD(product);
            }
        });
        thread.start();
    }

    private void createProduct() {

        try {
            Product product=new Product();
            getView().showProduct(product);

        } catch (RetrofitError  retrofitError){
            //TODO: mostrar alert

        }/*finally {
            getView().hideProgress();
        }*/



    }

    private void createProductDB(Product product) {

        try {

            boolean isSalved= Database.producDao.createProduct(product);
            getView().showProduct(product);
        } catch (RetrofitError  retrofitError){
            //TODO: mostrar alert

        }/*finally {
            getView().hideProgress();
        }*/



    }

    private void createNewProductService(Product product){
        try{
            productRepository.onCreateProduct(product);
            getView().showProduct(product);
        }catch (RetrofitError retrofitError){
            getView().showProgress("ERROR");
        }
    }



    private void createNewProductBD(Product product){
        try{
            boolean respuetas=Database.producDao.createProduct(product);
             getView().showProgress("Se creo");
        }catch (RetrofitError retrofitError){
            getView().showProgress("no Se creo");
        }
    }

}
