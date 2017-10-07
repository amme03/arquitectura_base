package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.UpdateResponse;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailProductView;

import retrofit.RetrofitError;

/**
 * Created by ana.marrugo on 23/09/2017.
 */

public class DetailProductPresenter extends BasePresenter<IDetailProductView>{

    private IProductRepository productRepository;
    public  DetailProductPresenter(IProductRepository iProductRepository ){
        this.productRepository=iProductRepository;

    }

    public void deleteProduct(String id) {
        if(getValidateInternet().isConnected()){
            createThreadDeleteProduct(id);
        }else{
            getView().showToast(R.string.validate_internet);
        }

    }


    public void createThreadDeleteProduct(final String id){
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
        @Override
            public void run() {
            deleteProductRepository(id);
        }         });
        thread.start();

    }



    public void  deleteProductRepository(String id){
      try{
        DeleteResponse deleteResponse= productRepository.deleteProduct(id);

        if(deleteResponse.getStatus()){
            getView().showToast(R.string.corret);
        }else{
            getView().showAlertDialogError(R.string.error);
        }
    } catch (RepositoryError repositoryError) {
          getView().showToast(repositoryError.getMessage());
      }
    }


    public void  updateProductRepository(String id,Product product){
        try{
            UpdateResponse updateResponse= productRepository.updateProduct(id,product);

           if(updateResponse.isStatus()){
                getView().showToast(R.string.corret);
            }else{
                getView().showAlertDialogError(R.string.error);
            }
        } catch (RepositoryError repositoryError) {
            getView().showToast(repositoryError.getMessage());
        }
    }

    public void updateProduct(String productId, Product product) {
        if(getValidateInternet().isConnected()){
            createThreadUpdateProduct(productId, product);
        }
    }

    public void createThreadUpdateProduct(final String id, final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                updateProductRepository(id, product);
            }
        });
        thread.start();
    }
}
