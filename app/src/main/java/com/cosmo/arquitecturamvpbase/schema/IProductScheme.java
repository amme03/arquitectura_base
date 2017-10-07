package com.cosmo.arquitecturamvpbase.schema;

/**
 * Created by ana.marrugo on 30/09/2017.
 */

public interface IProductScheme {

    String PRODUCT_TABLE = "products";
    String PRODUCT_TABLE_COLUNM_ID = "_id";
    String PRODUCT_TABLE_COLUNM_NAME = "name";
    String PRODUCT_TABLE_COLUNM_DESCRIPTION = "description";
    String PRODUCT_TABLE_COLUNM_PRICE = "price";
    String PRODUCT_TABLE_COLUNM_QUANTITY = "quantity";


    String PRODUCT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE +
                    " ( "+
                    PRODUCT_TABLE_COLUNM_ID + "  TEXT PRIMARY KEY, " +
                    PRODUCT_TABLE_COLUNM_NAME + " TEXT UNIQUE NOT NULL, " +
                    PRODUCT_TABLE_COLUNM_DESCRIPTION + " TEXT, " +
                    PRODUCT_TABLE_COLUNM_PRICE + " TEXT, " +
                    PRODUCT_TABLE_COLUNM_QUANTITY + " TEXT " +
                    " );";

    String[] PRODUCT_COLUMNS = new String[]{
            PRODUCT_TABLE_COLUNM_ID, PRODUCT_TABLE_COLUNM_NAME, PRODUCT_TABLE_COLUNM_DESCRIPTION, PRODUCT_TABLE_COLUNM_PRICE, PRODUCT_TABLE_COLUNM_PRICE
    };

}
