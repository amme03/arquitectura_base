package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.presenter.CreateCustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

import java.util.ArrayList;


public class CreateCustomerActivity extends BaseActivity<CreateCustomerPresenter> implements ICreateCustomerView {


    private TextInputEditText name;
    private TextInputEditText surname;
    private TextInputEditText phoneDescripcion;
    private TextInputEditText phoneNumber;
    private TextInputEditText coordinateX;
    private TextInputEditText coordinateY;
    private Button createCustomer_bto;

    private ContentLoadingProgressBar progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        setPresenter(new CreateCustomerPresenter(new CustomerRepository()));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        loadView();


    }

    private void loadView() {
        name = (TextInputEditText) findViewById(R.id.customer_name);
        surname = (TextInputEditText) findViewById(R.id.customer_surname);
        phoneDescripcion = (TextInputEditText) findViewById(R.id.customer_phonedescription);
        phoneNumber = (TextInputEditText) findViewById(R.id.customer_phonenumber);
        coordinateX = (TextInputEditText) findViewById(R.id.customer_longitud);
        coordinateY = (TextInputEditText) findViewById(R.id.customer_latitud);
        createCustomer_bto=(Button)findViewById(R.id.createCustomer_bto);
        createCustomer_bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreationCustomer(view);
            }
        });
    }


    public void CreationCustomer(View view)
    {

        getPresenter().createNewCustomer(getCustomer()
        );
    }

    @Override
    public void showCustomer(Customer customer) {

    }


    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setName(name.getText().toString());
        customer.setSurname(surname.getText().toString());
        ArrayList<Phone> phoneLists = new ArrayList<Phone>();
        Phone phone = new Phone();
        phone.setDescripcion(phoneDescripcion.getText().toString());
        phone.setNumber(phoneNumber.getText().toString());
        Location location = new Location();
        location.setType("Casa");
        Double coordinate[] = {
                Double.valueOf(coordinateX.getText().toString()),
                Double.valueOf(coordinateY.getText().toString())};
        location.setCoodinates(coordinate);
        phone.setLocation(location);

        phoneLists.add(phone);
        customer.setPhoneList(phoneLists);

      return customer;
    }




    
    
}