package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.UserAdapter;

import java.util.ArrayList;



public class CustomerActivity extends BaseActivity<CustomerPresenter> implements ICustomerView {


    private ListView userList;
    private UserAdapter userAdapter;
    private FloatingActionButton userCreateButton;


    private ContentLoadingProgressBar progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setPresenter(new CustomerPresenter(new CustomerRepository()));
        getPresenter().inject(this, getValidateInternet());
        //createProgressDialog();
        userList = (ListView) findViewById(R.id.user_listView);
        progress = (ContentLoadingProgressBar) findViewById(R.id.progress);

         userCreateButton = (FloatingActionButton) findViewById(R.id.button_creacion);
        userCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomerActivity(view);
            }
        });
        progress.show();
        getPresenter().validateInternetProduct();

    }

    @Override
    public void showUserList(final ArrayList<Customer> customerArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.hide();
                callAdapter(customerArrayList);
            }
        });

    }

    @Override
    public void showAlertDialog(int sldld) {

    }

    @Override
    public void showToast(int mensaje) {

    }

    @Override
    public void showToast(String mensaje) {

    }


    private void showAlertDialog(final int title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getShowAlertDialog().showAlertDialog(title, message, false, R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().getUserList();
                    }
                }, R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        });
    }


    private void callAdapter(final ArrayList<Customer> customerArrayList) {
        userAdapter =  new UserAdapter(this, R.id.user_listView, customerArrayList);
        userList.setAdapter(userAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerActivity.this, CustomerDetailActivity.class);
                intent.putExtra(Constants.ITEM_USER, customerArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    public void createCustomerActivity(View view) {
        Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(this, CreateCustomerActivity.class);
               startActivity(intent);
           }

}
