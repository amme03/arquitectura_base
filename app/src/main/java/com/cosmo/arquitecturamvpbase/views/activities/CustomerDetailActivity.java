package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.presenter.DetailCustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.UserDetalleAdapter;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 05/10/2017.
 */

public class CustomerDetailActivity extends BaseActivity<DetailCustomerPresenter> implements IDetailCustomerView {


    private TextView nameValue;
    private TextView idValue;
    private TextView surnameValue;

    private Customer customer;

    //phoneList
    private ListView phoneList;
    private UserDetalleAdapter userDetalleAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_user_detail);
        setPresenter(new DetailCustomerPresenter(new CustomerRepository()));
        getPresenter().inject(this, getValidateInternet());
        phoneList = (ListView) findViewById(R.id.phone_listView);


        customer = (Customer) getIntent().getSerializableExtra(Constants.ITEM_USER);
        ArrayList<Phone> list= new ArrayList<Phone>();
        Phone phone= new Phone();
        phone.setNumber("4534534");
        phone.setDescripcion("CAsa");

        Phone phone1= new Phone();
        phone1.setNumber("4534534344");
        phone1.setDescripcion("CAsa1");
        list.add(phone);
        list.add(phone1);
        customer.setPhoneList(list);
        loadView();
        setDataItem();


    }

    private void setDataItem() {
        nameValue.setText(customer.getName());
        idValue.setText(customer.getId()); 
        surnameValue.setText(customer.getSurname());


    }

    private void loadView() {

        nameValue = (TextView) findViewById(R.id.user_detail_name_value);
        idValue = (TextView) findViewById(R.id.user_detail_description_value);
        surnameValue = (TextView) findViewById(R.id.user_detail_quantity_value);
        phoneList=(ListView)findViewById(R.id.user_listView) ;


    }




    @Override
    public void showAlertDialog(int validate_internet) {
        Toast.makeText(this, "mun", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlertDialogError(int validate_internet) {
        Toast.makeText(this, " ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int validate_internet) {

    }

    @Override
    public void showToast(String validate_internet) {

    }

    @Override
    public void showPhoneList(final ArrayList<Phone> phoneArrayList) {
        System.out.print("phoneArrayList "+phoneArrayList.size());


        if (phoneArrayList!=null){
            Toast.makeText(this, "no null", Toast.LENGTH_SHORT).show(); }
        else{
            Toast.makeText(this, "nullooo", Toast.LENGTH_SHORT).show();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(phoneArrayList != null){
                    callAdapter(phoneArrayList);
                }else{
                    Toast.makeText(null, "run   -----  nullooo", Toast.LENGTH_SHORT).show();
                    callAdapter(customer.getPhoneList());
                }
            }
        });

    }

    private void callAdapter(final ArrayList<Phone> phoneArrayList) {
        userDetalleAdapter =  new UserDetalleAdapter(this, R.id.phone_listView, phoneArrayList);

        if (phoneArrayList!=null){
            Toast.makeText(this, " phoneList nullooo alex ;) "+phoneList.toString(), Toast.LENGTH_SHORT).show();
             phoneList.setAdapter(userDetalleAdapter);
        }else{
            Toast.makeText(this, " phoneList nullooo", Toast.LENGTH_SHORT).show();
        }

    }
}
