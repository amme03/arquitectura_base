package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.ProductPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.ProductAdapter;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductActivity extends BaseActivity<ProductPresenter> implements IProductView {


    private ListView productList;
    private ProductAdapter productAdapter;
    private ContentLoadingProgressBar progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setPresenter(new ProductPresenter());
        getPresenter().inject(this, getValidateInternet());
        //createProgressDialog();
        productList = (ListView) findViewById(R.id.product_listView);
        progress = (ContentLoadingProgressBar) findViewById(R.id.progress);
        progress.show();
        getPresenter().validateInternetProduct();

    }

    @Override
    public void showProductList(final ArrayList<Product> productArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.hide();
                callAdapter(productArrayList);
            }
        });

    }
    @Override
    public void showAlertDialog(int title, int message) {
           showAlertDialog(title,getResources().getString(message));
    }


    private void showAlertDialog(final int title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getShowAlertDialog().showAlertDialog(title, message, false, R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().getListProduct();
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


    private void callAdapter(final ArrayList<Product> productArrayList) {
        productAdapter =  new ProductAdapter(this, R.id.product_listView, productArrayList);
        productList.setAdapter(productAdapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductActivity.this, DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT,productArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
