package com.cosmo.arquitecturamvpbase;

import android.app.Application;

import com.cosmo.arquitecturamvpbase.helper.Database;

/**
 * Created by ana.marrugo on 30/09/2017.
 */

public class App extends Application {

    public static Database mdb;

    @Override
    public  void onCreate(){
        super.onCreate();
        mdb=new Database(this);
        mdb.open();

    }


    @Override
    public  void onTerminate(){

        mdb.close();
        super.onTerminate();

    }

}
