package com.alsaleh.naser.supermarketmanagment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.alsaleh.naser.supermarketmanagment.LVAdapter;
import com.alsaleh.naser.supermarketmanagment.ProductDetails;
import com.alsaleh.naser.supermarketmanagment.R;

import java.util.ArrayList;


public class SearchProductFrag extends Fragment {
    ListView listView;
    EditText name, code;
    Button search;
    DataUtilities dataUtilities;
    ArrayList<ProductDetails> products;
    LVAdapter adapter;

    public SearchProductFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchProductFrag newInstance() {
        return new SearchProductFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateView(view);
        // display the products details  in a listView //
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter != null)
                 adapter.deleteList();
                boolean nameStatus = name.getText().toString().equalsIgnoreCase("");
                boolean codeStatus = code.getText().toString().equalsIgnoreCase("");
                if( nameStatus && codeStatus){
                    Toast.makeText(getContext(), "Please, enter the required information", Toast.LENGTH_SHORT).show();
                }else if(!nameStatus && codeStatus){
                   getProducts(name.getText().toString(),"name");
                }else {
                    getProducts(code.getText().toString(), "code");
                }
                if(products!=null){
                    adapter = new LVAdapter(products,getContext(),R.layout.lslayout);
                    listView.setAdapter(adapter);

                }
                name.setText("");
                code.setText("");
            }
        });

    }

    // inflate the view of search product fragment
    public void inflateView(View view){
        listView = view.findViewById(R.id.search_prod_frag_LV_id);
        name = view.findViewById(R.id.search_prod_frag_ET_id_name);
        code = view.findViewById(R.id.search_prod_frag_ET_id_code);
        search = view.findViewById(R.id.search_Product_Frag_BTN_id_Search);
        dataUtilities = DataUtilities.getInstance(getContext());
    }

    // fetch the products if there are already products which have this name or this code//
    public void getProducts(String name,String which){
         products = dataUtilities.getAllProducts(name, which);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(products!= null)
        products.clear();
    }

}