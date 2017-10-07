package com.cosmo.arquitecturamvpbase.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.CreateProductPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;


/**
 * Created by ana.marrugo on 19/09/2017.
 */

public class CreateProductActivity  extends BaseActivity<CreateProductPresenter> implements ICreateProductView, TextWatcher{
    private EditText productoEditText;
    private TextView textView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_servicio_rest_activiti);

        /* Initializing views */
        productoEditText = (EditText) findViewById(R.id.text_servicio_rest);
        textView = (TextView) findViewById(R.id.edit_servicio_est);
        textView.setVisibility(View.GONE);


    }


    @Override
    public void showProgress(int message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProduct(Product product) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
         textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            textView.setVisibility(View.GONE);
        } else{
            textView.setText("Usted no ha ingresado datos : " + productoEditText.getText());
        }
    }
}

