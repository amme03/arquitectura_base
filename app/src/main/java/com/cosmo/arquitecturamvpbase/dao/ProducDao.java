package com.cosmo.arquitecturamvpbase.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.providers.DbContentProvider;
import com.cosmo.arquitecturamvpbase.schema.IProductScheme;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 30/09/2017.
 */

public class ProducDao extends DbContentProvider implements IProductScheme,IproducDao {

    private  Cursor cursor;
    private ContentValues initialValues;
    public ProducDao(SQLiteDatabase db){

     super(db);}

    @Override
    public ArrayList<Product> fetchAllProdutc() {
        ArrayList<Product> productArrayList= new ArrayList<>();
        cursor=super.query(PRODUCT_TABLE,PRODUCT_COLUMNS,null,null,PRODUCT_TABLE_COLUNM_NAME);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Product product=cursorToEntity(cursor);
                productArrayList.add(product);
                cursor.moveToNext();
            }

            cursor.close();

        }
        return productArrayList;
    }

    @Override
    public Boolean createProduct(Product product) {
       try{
       return   super.insert(PRODUCT_TABLE,getContentValue())>=0;
       }catch (SQLiteConstraintException ex)
       {  Log.e("DbErrorCreateProduct",ex.getMessage());

           return  false;
       }

    }

    @Override
    protected Product cursorToEntity(Cursor cursor) {
        Product product= new Product();
        int idIndex;
        if (cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_ID)!=-1){
            idIndex=cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_ID);
            product.setId(cursor.getString(idIndex));
        }
        int nameIndex;
        if (cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_NAME)!=-1){
            nameIndex=cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_NAME);
            product.setId(cursor.getString(nameIndex));
        }

        int descriptionIndex;
        if (cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_DESCRIPTION)!=-1){
            descriptionIndex=cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_DESCRIPTION);
            product.setId(cursor.getString(descriptionIndex));
        }

        int quantyIndex;
        if (cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_QUANTITY)!=-1){
            quantyIndex=cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_QUANTITY);
            product.setId(cursor.getString(quantyIndex));
        }

        int priceIndex;
        if (cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_PRICE)!=-1){
            priceIndex=cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_PRICE);
            product.setId(cursor.getString(priceIndex));
        }

        return product;
    }


    public ContentValues getContentValue() {
        return initialValues;
    }

    public void setContentValueProduct(Product product)
    {
        initialValues= new ContentValues();

        initialValues.put(PRODUCT_TABLE_COLUNM_ID, product.getId());
        initialValues.put(PRODUCT_TABLE_COLUNM_NAME, product.getName());
        initialValues.put(PRODUCT_TABLE_COLUNM_DESCRIPTION, product.getDescription());
        initialValues.put(PRODUCT_TABLE_COLUNM_PRICE, product.getPrice());
        initialValues.put(PRODUCT_TABLE_COLUNM_QUANTITY, product.getQuantity());
    }
}
