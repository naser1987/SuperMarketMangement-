package com.alsaleh.naser.supermarketmanagment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LVAdapter extends BaseAdapter {
    ArrayList<ProductDetails> productdetails;
    Context context;
    int layout_id;
    TextView name,code, prodDate, expireDate;

    public LVAdapter(ArrayList<ProductDetails> productdetails, Context context, int layout_id) {
        this.productdetails = productdetails;
        this.context = context;
        this.layout_id = layout_id;
    }

    @Override
    public int getCount() {
        return productdetails.size();
    }

    @Override
    public ProductDetails getItem(int i) {
        return productdetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
           v = LayoutInflater.from(context).inflate(R.layout.lslayout,null,false);
        }
        name = v.findViewById(R.id.lslayout_TV_id_name);
        code = v.findViewById(R.id.lslayout_TV_id_code);
        prodDate = v.findViewById(R.id.lslayout_TV_id_productionDate);
        expireDate = v.findViewById(R.id.lslayout_TV_id_expireDate);
        name.setText(productdetails.get(i).getName());
        code.setText(productdetails.get(i).getCode());
        prodDate.setText(productdetails.get(i).getProdDate());
        expireDate.setText(productdetails.get(i).getExpireDate());

        return v;
    }
    public void  deleteList(){
        if(productdetails != null )
            productdetails.clear();
        this.notifyDataSetChanged();
    }


}
