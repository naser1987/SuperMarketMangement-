package com.alsaleh.naser.supermarketmanagment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FindListViewPriceAdapter extends BaseAdapter {
    private final ArrayList<ProductMarketPrice> productMarketPrices ;
    private final Context context;
    private final int layout_id;
    TextView productName, marketName, price;

    public FindListViewPriceAdapter(ArrayList<ProductMarketPrice> pProductMarketPrices, Context pContext, int pLayout_id) {
        this.productMarketPrices = pProductMarketPrices;
        this.context = pContext;
        this.layout_id = pLayout_id;
    }

    @Override
    public int getCount() {
        return productMarketPrices.size();
    }

    @Override
    public ProductMarketPrice getItem(int i) {
        return productMarketPrices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = LayoutInflater.from(context).inflate(layout_id,null,false);
        }
        productName = v.findViewById(R.id.find_price_Tv_id_productName);
        marketName = v.findViewById(R.id.find_price_Tv_id_marketName);
        price = v.findViewById(R.id.find_price_Tv_id_price);

        productName.setText(productMarketPrices.get(i).getProductName());
        marketName.setText(productMarketPrices.get(i).getMarketName());
        price.setText(productMarketPrices.get(i).getPrice());
        return v;
    }
}
