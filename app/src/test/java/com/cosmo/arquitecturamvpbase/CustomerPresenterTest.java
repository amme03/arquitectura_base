package com.cosmo.arquitecturamvpbase;

/**
 * Created by ana.marrugo on 03/10/2017.
 */


import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ICustomerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ana.marrugo on 23/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerPresenterTest {

    @Mock
    IValidateInternet validateInternet;


    @Mock
    ICustomerRepository userRepository;
    @Mock
    ICustomerView userView;

    CustomerPresenter customerPresenter;


    private Customer getUser(){
        Customer customer = new Customer();
        customer.setName("ANA");

        ArrayList<Phone> telefonos= new ArrayList<Phone>();
        Phone telefono= new Phone();
        telefono.setDescripcion("Casa");

        Location locacion= new Location();
        locacion.setType("tipe");
        Double[] coordenada={233.55,344.55};
        locacion.setCoodinates(coordenada);
        telefono.setLocation(locacion);
        telefono.setNumber("122333");
        telefonos.add(telefono);

        customer.setPhoneList(telefonos);

        return customer;

    }

    @Before
    public  void  setUp() throws Exception{
        customerPresenter = Mockito.spy(new CustomerPresenter(userRepository));
        customerPresenter.inject(userView,validateInternet);

    }


    @Test
    public  void methodUserListWithConnection(){

        when(validateInternet.isConnected()).thenReturn(true);
        customerPresenter.validateInternetProduct();
        verify(customerPresenter).createThreadUser();
        verify(userView,never()).showAlertDialog(R.string.validate_internet);



    }

    @Test
    public  void methodUserListWithNotConnection(){

        when(validateInternet.isConnected()).thenReturn(false);
        customerPresenter.validateInternetProduct();



    }

    @Test
    public void methodSelectUserShowsAlertRepositoryReturnError() throws RepositoryError {

        RepositoryError repositoryError= new RepositoryError(Constants.DEFAUL_ERROR);
        when(userRepository.getUserList()).thenThrow(repositoryError);
        customerPresenter.getUserList();
        verify(userView).showToast(repositoryError.getMessage());

    }


    @Test
    public void methodSelectUserShowsAlertRepository() throws RepositoryError {
        ArrayList<Customer> customers = new ArrayList<>();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        when(userRepository.getUserList()).thenReturn(customers);
        customerPresenter.getUserList();
         verify(userView).showUserList(customers);
        verify(userView, never()).showToast(repositoryError.getMessage());
    }



}
