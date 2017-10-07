package com.cosmo.arquitecturamvpbase;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.UpdateResponse;
import com.cosmo.arquitecturamvpbase.presenter.DetailProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailProductView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ana.marrugo on 23/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IProductRepository productRepository;
    @Mock
    IDetailProductView detilProductView;

    DetailProductPresenter detailProductPresenter;

    private Product getProduct(){
        Product product = new Product();
        product.setDescription("Empanada ANA");
        product.setName("Empanada ANA");
        product.setId("s733275022");
        product.setPrice("1500");
        return product;

    }

  @Before
public  void  setUp() throws Exception{
  detailProductPresenter= Mockito.spy(new DetailProductPresenter(productRepository));
      detailProductPresenter.inject(detilProductView,validateInternet);
  }
  @Test
  public  void methodDeleteProductWithConnection(){
      String id="13gh1jhhd232";
      when(validateInternet.isConnected()).thenReturn(true);
      detailProductPresenter.deleteProduct(id);
      verify(detailProductPresenter).createThreadDeleteProduct(id);
      verify(detilProductView,never()).showAlertDialog(R.string.validate_internet);

  }


    @Test
    public void methodDeleteProduct(){
        String id="13gh1jhhd232";
        when(validateInternet.isConnected()).thenReturn(false);
        detailProductPresenter.deleteProduct(id);
        verify(detilProductView).showAlertDialog(R.string.validate_internet);
        verify(detailProductPresenter, never()).createThreadDeleteProduct(id);

    }


    @Test
    public void methodDeleteCallProduct() throws RepositoryError{
        DeleteResponse deleteResponse= new DeleteResponse();
        deleteResponse.setStatus(true);
        String id="13gh1jhhd232";
        when(productRepository.deleteProduct(id)).thenReturn(deleteResponse);
        detailProductPresenter.deleteProductRepository(id);
        Assert.assertTrue(deleteResponse.getStatus());
        verify(detilProductView).showToast(R.string.corret);
        verify(detilProductView, never()).showAlertDialogError(R.string.error);

    }

    @Test
    public void methodCreateThreadShouldShowsProgressDIalog(){
        String id="13gh1jhhd232";
        detailProductPresenter.createThreadDeleteProduct(id);
        verify(detilProductView).showProgress("Cargando...");

    }

    @Test
    public void methodDeleteProducShouldShowsAlertRepositoryReturnError() throws RepositoryError {
        String id="13gh1jhhd232";
        RepositoryError repositoryError= new RepositoryError(Constants.DEFAUL_ERROR);
        when(productRepository.deleteProduct(id)).thenThrow(repositoryError);
        detailProductPresenter.deleteProductRepository(id);
        verify(detilProductView).showToast(repositoryError.getMessage());
        verify(detilProductView, never()).showToast(R.string.corret);
        verify(detilProductView, never()).showToast(R.string.error_delete_produt);



    }
    @Test
    public  void methodUpdateProductWithConnection() {
        Product product = getProduct();
        when(validateInternet.isConnected()).thenReturn(true);
        detailProductPresenter.updateProduct(product.getId(), product);
        verify(detailProductPresenter).createThreadUpdateProduct(product.getId(), product);
        // verify(detilProductView,never()).showAlertDialog(R.string.validate_internet);
    }


    @Test
    public void methodUpdateProducShouldShowsAlertRepositoryReturnError() throws RepositoryError {
        String id="13gh1jhhd232";
        UpdateResponse updateResponse=new UpdateResponse();
        updateResponse.setStatus(true);
        when(productRepository.updateProduct(getProduct().getId(),getProduct())).thenReturn(updateResponse);
        detailProductPresenter.updateProductRepository(getProduct().getId(),getProduct());
         Assert.assertTrue(updateResponse.isStatus());
        verify(detilProductView).showToast(R.string.corret);
        verify(detilProductView, never()).showAlertDialogError(R.string.error);


    }
}
