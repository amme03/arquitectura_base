package com.cosmo.arquitecturamvpbase.views.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Phone;

import java.util.ArrayList;


public class UserDetalleAdapter extends ArrayAdapter<Phone> {

    private ArrayList<Phone> phoneArrayList;
    private Activity context;
    private Phone phone;
    private TextView description;
    private TextView number;


    public UserDetalleAdapter(Activity context, int resource, ArrayList<Phone> phoneArrayList) {
        super(context, resource, phoneArrayList);
        this.context = context;
        this.phoneArrayList = phoneArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        this.phone = this.phoneArrayList.get(position);
        loadView(convertView);
        description.setText(phone.getDescripcion());
        number.setText(phone.getNumber());
        return convertView;
    }

    private void loadView(View convertView){
        description = (TextView) convertView.findViewById(R.id.item_description_phone);
        number = (TextView) convertView.findViewById(R.id.item_number_phone);
    }
}
