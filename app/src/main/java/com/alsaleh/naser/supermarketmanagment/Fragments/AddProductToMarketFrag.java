package com.alsaleh.naser.supermarketmanagment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.alsaleh.naser.supermarketmanagment.ProductDetails;
import com.alsaleh.naser.supermarketmanagment.ProductMarketDetails;
import com.alsaleh.naser.supermarketmanagment.R;

import java.util.ArrayList;

public class AddProductToMarketFrag extends Fragment {

    DataUtilities dataUtilities;
    Spinner marketListSpinner;
    Button addToMarket, cancel;
    ArrayList<String> marketNames = new ArrayList<>();
    EditText code,price;
    TextView prodDate,expireDate,name;
    ProductDetails productDetails;

    public AddProductToMarketFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddProductToMarketFrag newInstance() {
        return new AddProductToMarketFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product_to_market, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateView(view);
        //fill the spinner
        if(fillMarketList()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, marketNames);
            marketListSpinner.setAdapter(adapter);
        }

        code.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 14) {
                    // either return arraylist with products or return null
                    productDetails = dataUtilities.getProduct(code.getText().toString());
                    if ( productDetails != null) {
                        Log.d("frag  ",productDetails.getName());
                        Log.d("frag  " ,productDetails.getCode());
                        addToMarket.setEnabled(true);
                        fillProductFields(productDetails);
                    }else {
                        Toast.makeText(getContext(),"there is no product which has this code",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Toast.makeText(getContext(),"prod details code ",Toast.LENGTH_LONG).show();
        // click to insert a product with its price to a specific market or notify that it already exists
        addToMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pri = String.valueOf(price.getText());
                int market_id = marketListSpinner.getSelectedItemPosition();
                if( productDetails != null && !( pri.equalsIgnoreCase("") )&& market_id > -1 ) {
                    long res = dataUtilities.insertProdToMarket(new ProductMarketDetails(market_id, productDetails.getCode(), pri));
                    if ( res != -1) {
                        clearFiled();
                        code.setEnabled(true);
                    } else {
                        Toast.makeText(getActivity(), "the product already exists in the market " + marketNames.get(marketListSpinner.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "please, fill all the required fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        // cancel event the inserted data and clear the fields
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFiled();
                code.setEnabled(true);
                addToMarket.setEnabled(false);
            }
        });

    }

    // get Market names from the data base //
    public boolean fillMarketList(){
        dataUtilities = DataUtilities.getInstance(getActivity());
        dataUtilities.openConnect();
        marketNames = dataUtilities.getMarkets();
        //Toast.makeText(getActivity(),marketNames.size()+" here ", Toast.LENGTH_SHORT).show();
        return marketNames.size() != 0;
    }

    // fill the product fields

    public void fillProductFields(ProductDetails productDetails){

        name.setText(productDetails.getName());
        // the code will paste itself that makes the application always check the product if it exists infinite loop
        code.setEnabled(false);
        prodDate.setText(productDetails.getProdDate());
        expireDate.setText(productDetails.getExpireDate());
    }

    // clear the fields
    public void clearFiled(){
        price.setText("");
        code.setText("");
        name.setText("");
        expireDate.setText("");
        prodDate.setText("");
        marketListSpinner.setId(-1);
    }

    // inflate the view
    public  void inflateView(View view){
        // spinner for display the existing markets
        marketListSpinner = view.findViewById(R.id.add_prod_to_market_spinner);
        // EditTexts to inter the price and the code
        code = view.findViewById(R.id.add_pro_to_market_ET_id_code);
        price = view.findViewById(R.id.add_prod_to_market_Et_id_price);
        // TextViews to display the product details
        name = view.findViewById(R.id.add_pro_to_market_Tv_id_name);
        prodDate = view.findViewById(R.id.add_prod_market_TV_id_production_date);
        expireDate = view.findViewById(R.id.add_prod_market_TV_id_expire_date);

       // Buttons
        addToMarket = view.findViewById(R.id.add_prod_to_market_BTN_add);
        cancel = view.findViewById(R.id.add_prod_to_market_BTN_cancel);
    }

}