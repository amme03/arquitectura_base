package com.cosmo.arquitecturamvpbase;

/**
 * Created by ana.marrugo on 03/10/2017.
 */


import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.presenter.CreateCustomerPresenter;
import com.cosmo.arquitecturamvpbase.presenter.CreateProductPresenter;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateCustomerView;
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
public class CustomerPresenterCreateTest {

    @Mock
    IValidateInternet validateInternet;


    @Mock
    CustomerRepository customerRepository;
    @Mock
    ICreateCustomerView customerView;

    CreateCustomerPresenter customerPresenter;


    private Customer getCustomer(){
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
        customerPresenter = Mockito.spy(new CreateCustomerPresenter(customerRepository));
        customerPresenter.inject(customerView,validateInternet);


    }


    @Test
    public  void methodCreateCustomerWithConnection(){
         Customer customer=getCustomer();
        when(validateInternet.isConnected()).thenReturn(true);
        customerPresenter.createNewCustomer(customer);
        verify(customerPresenter).createThreadCreateCustomer(customer);
        verify(customerView,never()).showProgress(R.string.validate_internet);


    }

    @Test
    public  void methodCustomersListWithNotConnection(){

        when(validateInternet.isConnected()).thenReturn(false);
        customerPresenter.validateInternetCustomer(getCustomer());

    }

    @Test
    public void methodCreateCustomerShowsAlertRepositoryReturnError() throws RepositoryError {
        Customer customer = getCustomer();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        when(customerRepository.onCreateCustomer(customer)).thenThrow(repositoryError);
        customerPresenter.createNewCustomer(customer);

    }


    @Test
    public void methodCreateCustomerShowsAlertRepository() throws RepositoryError {
        Customer customer = getCustomer();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        when(customerRepository.onCreateCustomer(getCustomer())).thenReturn(customer);
        customerPresenter.createNewCustomer(customer);
        verify(customerView).showProgress(R.string.error);

    }



}
