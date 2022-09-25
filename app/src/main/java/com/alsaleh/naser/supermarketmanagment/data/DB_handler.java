package com.alsaleh.naser.supermarketmanagment.data;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DB_handler extends SQLiteAssetHelper {
public static final String  DB_name ="SuperMarket.db";
public static final String  DB_table_users_name = "users";
public static final String  DB_table_markets_name = "markets";
public static final String  DB_table_products_name = "products";
public static final String  DB_table_market_product_name = "market_product";
public static final String  DB_col_user_name = "user_name";
public static final String  DB_col_user_password = "password";
public static final String  DB_col_prod_code = "code";
public static final String  DB_col_prod_name = "name";
public static final String  DB_col_prod_production_date = "productionDate";
public static final String  DB_col_prod_expire_date = "expireDate";
public static final String  DB_col_prod_supplier = "supplier";
public static final String  DB_col_markets_name = "name";
public static final String  DB_col_market_prod_market_id = "market_id";
public static final String  DB_col_market_prod_product_code = "productCode";
public static final String  DB_col_market_prod_price = "price";
public static final int  DB_version = 2;

    public DB_handler(Context context) {
        super(context, DB_name, null, DB_version);
    }
}
