package com.alsaleh.naser.supermarketmanagment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alsaleh.naser.supermarketmanagment.MarketDetails;
import com.alsaleh.naser.supermarketmanagment.ProductDetails;
import com.alsaleh.naser.supermarketmanagment.ProductMarketDetails;
import com.alsaleh.naser.supermarketmanagment.ProductMarketPrice;

import java.util.ArrayList;

public class DataUtilities {
    private  final DB_handler handler;
    private SQLiteDatabase database;
    private static DataUtilities dataUtilities;
    Context context;
    Cursor cursor;
    ArrayList<String> marketNames= new ArrayList<>();
    ArrayList<ProductDetails> products= new ArrayList<>();
    ArrayList<ProductMarketPrice> productMarketPrices= new ArrayList<>();


    public ArrayList<ProductMarketPrice> getProductMarketPrice(String name){

        String sql =" SELECT * FROM products JOIN markets JOIN market_product ON products.code = market_product.productCode AND markets.id = market_product.market_id WHERE products.name = '"+name+"'";
        if(productMarketPrices != null)
            productMarketPrices.clear();
        cursor = database.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            int i = 0;
             while( ( !cursor.isAfterLast() ) && ( i < 10 ) ){
                ProductMarketPrice productMarketPrice= new ProductMarketPrice();
                productMarketPrice.setProductName(cursor.getString(1));
                productMarketPrice.setMarketName(cursor.getString(6));
                productMarketPrice.setPrice(cursor.getString(12));
                productMarketPrices.add(productMarketPrice);
                cursor.moveToNext();
                i++;
            }
            cursor.close();
            return productMarketPrices;
        }
        return null;
    }


    private DataUtilities(Context pContext){
        this.handler = new DB_handler(pContext);
        this.context = pContext;
    }
    public static DataUtilities getInstance(Context pContext){
        if(dataUtilities == null)
            dataUtilities = new DataUtilities(pContext);
        return dataUtilities;
    }
    public void openConnect(){
        if(database == null)
        database = handler.getWritableDatabase();
    }
    public void close(){
        if (database != null)
            database.close();

    }
    public  boolean getUser(String pUserName,String pPassword){
        cursor = database.rawQuery("SELECT * FROM "+DB_handler.DB_table_users_name+" WHERE "+DB_handler.DB_col_user_name +" = '"+pUserName+"'",null);
        if(cursor!=null && cursor.moveToFirst()){

           boolean bool =  (cursor.getString(cursor.getColumnIndex("user_name")).equalsIgnoreCase(pUserName)) && (cursor.getString(cursor.getColumnIndex("password")).equalsIgnoreCase(pPassword));
            cursor.close();
            return bool;
        }
        return false;
    }

    // Insert New Market
    public long insertNewMarket(MarketDetails marketDetails){
        // check if the supermarket already exists or not
        cursor =database.rawQuery("SELECT * FROM "+ DB_handler.DB_table_markets_name+" WHERE "+ DB_handler.DB_col_markets_name  + " = '"+marketDetails.getMarketName()+"'",null);

        //Log.d("cursor", cursor.getString(0));
        if(cursor!=null && cursor.moveToFirst()){
            //   the supermarket exists
            cursor.close();
            return -1;

        }else{
          // the supermarket does not exist and it has been inserted into database
        ContentValues market = new ContentValues();
        market.put("name",marketDetails.getMarketName());
        market.put("address",marketDetails.getMarketAddress());
        market.put("phone",marketDetails.getMarketPhoneNumber());
            return database.insert("markets",null, market);
        }
    }
    public long insertNewProduct(ProductDetails product){
        cursor =database.rawQuery("SELECT * FROM "+ DB_handler.DB_table_products_name+" WHERE "+ DB_handler.DB_col_prod_code  + " = '"+ product.getCode()+"'",null);
        if(cursor!=null && cursor.moveToFirst()){
            cursor.close();
            return -1;
        }else{
            // insert the product
            // the supermarket does not exist and it has been inserted into database
            ContentValues prod = new ContentValues();
            prod.put("code",product.getCode());
            prod.put("name",product.getName());
            prod.put("supplier",product.getSupplier());
            prod.put("productionDate",product.getProdDate());
            prod.put("expireDate",product.getExpireDate());

            long res=  database.insert("products",null, prod);
            cursor.close();
            return res;
        }

    }

    // Fetch the market //
    public ArrayList<String> getMarkets(){
        cursor = database.rawQuery("SELECT * FROM "+ DB_handler.DB_table_markets_name,null);
        marketNames.clear();
        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                // modify the add let only name and delete the rest now only for check
                marketNames.add(cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            cursor.close();
           return marketNames;
        }
           // database.close();
        return null;
    }
    public ProductDetails getProduct(String code){
        cursor =database.rawQuery("SELECT * FROM "+ DB_handler.DB_table_products_name+" WHERE "+ DB_handler.DB_col_prod_code  + " = '"+ code+"'",null);
        ProductDetails productDetails = new ProductDetails();

        if(cursor != null && cursor.moveToFirst()){
            productDetails.setName(cursor.getString(cursor.getColumnIndex("name")));
            productDetails.setProdDate(cursor.getString(cursor.getColumnIndex("productionDate")));
            productDetails.setExpireDate(cursor.getString(cursor.getColumnIndex("expireDate")));
            productDetails.setSupplier(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_supplier)));
            productDetails.setCode(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_code)));
            cursor.close();
            return productDetails;
        }
        // here i must return null not object because the object even null but it is a reference
        return null;
    }

    public long insertProdToMarket(ProductMarketDetails productMarketDetails){
        long res =-1;
        int marketId = productMarketDetails.getMarket_id()+1;
        String sql ="SELECT * FROM "+ DB_handler.DB_table_market_product_name+" WHERE "+ DB_handler.DB_col_market_prod_product_code + " = '"+ productMarketDetails.getProduct_code()+"'";
        cursor =database.rawQuery(sql,null);
        if( cursor!=null  &&  cursor.moveToFirst() ){
            while (!cursor.isAfterLast()) {
               int prodMarketId = cursor.getInt(cursor.getColumnIndex(DB_handler.DB_col_market_prod_market_id));
                if ( prodMarketId == marketId){
                    cursor.close();
                    return res;
                }
                cursor.moveToNext();
            }
            cursor.close();
        }

        // the supermarket does not exist and it has been inserted into database

        ContentValues prod = new ContentValues();
        prod.put(DB_handler.DB_col_market_prod_product_code,productMarketDetails.getProduct_code());
        prod.put(DB_handler.DB_col_market_prod_market_id,marketId);
        prod.put(DB_handler.DB_col_market_prod_price,productMarketDetails.getPrice());
        res = database.insert(DB_handler.DB_table_market_product_name,null, prod);
        return res;
    }

    public ArrayList<ProductDetails> getAllProducts(String name, String which){
        if(which.equalsIgnoreCase("name")) {
            cursor = database.rawQuery("SELECT * FROM " + DB_handler.DB_table_products_name + " WHERE "+ DB_handler.DB_col_prod_name+" = '" + name + "'", null);
        }else{
            cursor = database.rawQuery("SELECT * FROM " + DB_handler.DB_table_products_name + " WHERE " + DB_handler.DB_col_prod_code + " = '" + name + "'", null);
        }
        if(cursor != null ){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ProductDetails prod = new ProductDetails();
                prod.setName(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_name)));
                prod.setCode(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_code)));
                prod.setSupplier(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_supplier)));
                prod.setProdDate(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_production_date)));
                prod.setExpireDate(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_expire_date)));
                products.add(prod);
                cursor.moveToNext();
            }
            cursor.close();
            return products;
        }else{
            return null;
        }
    }
    public ArrayList<ProductDetails> getAllProducts(){
        cursor = database.rawQuery("SELECT * FROM " + DB_handler.DB_table_products_name , null);
        if(products != null)
            products.clear();
        if(cursor != null ){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ProductDetails prod = new ProductDetails();
                prod.setName(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_name)));
                prod.setCode(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_code)));
                prod.setSupplier(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_supplier)));
                prod.setProdDate(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_production_date)));
                prod.setExpireDate(cursor.getString(cursor.getColumnIndex(DB_handler.DB_col_prod_expire_date)));
                products.add(prod);
                cursor.moveToNext();
            }
            cursor.close();
            return products;
        }else{
        return null;
    }

    }

}
