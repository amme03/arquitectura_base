package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.dao.ProducDao;
import com.cosmo.arquitecturamvpbase.model.Product;


/**
 * Created by ana.marrugo on 30/09/2017.
 */

public class Database {
    private final Context context;
    private DatabaseHelper dbHelper;

    //DAO'S
    public static ProducDao producDao;

    public Database(Context context) {
        this.context=context;
    }

   public  Database open(){
       try{
           dbHelper= new DatabaseHelper(context);
           SQLiteDatabase sbd=dbHelper.getWritableDatabase();
           producDao= new ProducDao(sbd);
           return  this;

       } catch (SQLException ex){

           Log.e("SQL OPEN", ex.getMessage());
           throw  ex;
       }
   }

   public void close(){
       dbHelper.close();
   }

}
