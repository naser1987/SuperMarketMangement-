package com.alsaleh.naser.supermarketmanagment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.alsaleh.naser.supermarketmanagment.FindListViewPriceAdapter;
import com.alsaleh.naser.supermarketmanagment.ProductMarketPrice;
import com.alsaleh.naser.supermarketmanagment.R;

import java.util.ArrayList;


import java.util.Collections;


public class FindPriceFrag extends Fragment {


    TextView nameOfProduct;
    ListView productWithPrice;
    Button find;
    DataUtilities dataUtilities;

    ArrayList<ProductMarketPrice> productMarketPrices = new ArrayList<>();
    ArrayList<ProductMarketPrice> displayList = new ArrayList<>();
    public FindPriceFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FindPriceFrag newInstance() {
        return new FindPriceFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_price, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameOfProduct = view.findViewById(R.id.find_product_price_ET_id_name);
        productWithPrice = view.findViewById(R.id.find_product_price_LV_id);
        find = view.findViewById(R.id.find_product_price_BTN_id_Search);
        dataUtilities = DataUtilities.getInstance(getContext());
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayList.clear();
                getProductCode(nameOfProduct.getText().toString());
                nameOfProduct.setText("");

            }
        });
    }
    public void getProductCode(String name){
       // Log.d("name of product",name);
        dataUtilities.openConnect();
        productMarketPrices = dataUtilities.getProductMarketPrice(name);
        if(productMarketPrices != null){
            //productMarketPrices.toArray(array);
            sortProducts(productMarketPrices);
            setResultOnListview();
        }
    }

    // sorting the list depends on the price using comparable interface
    public void sortProducts(ArrayList <ProductMarketPrice> products){
        Collections.sort(products);
    }

    // display displayList in the listview
    public void setResultOnListview( ){
        displayList.add(new ProductMarketPrice("Product Name","Market Name","Price"));
        fillDisplayList();
        FindListViewPriceAdapter adapter = new FindListViewPriceAdapter(displayList,getContext(),R.layout.find_price_layout);
        productWithPrice.setAdapter(adapter);
    }

    // add sorting list to display list
    public void fillDisplayList(){
        displayList.addAll(productMarketPrices);
    }
}
