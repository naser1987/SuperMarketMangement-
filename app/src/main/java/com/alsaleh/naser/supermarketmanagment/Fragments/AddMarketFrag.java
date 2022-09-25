package com.alsaleh.naser.supermarketmanagment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.alsaleh.naser.supermarketmanagment.MarketDetails;
import com.alsaleh.naser.supermarketmanagment.R;


public class AddMarketFrag extends Fragment {

    EditText name,address,phoneNumber;
    int nameCharsCount,addressCharsCount,phoneCharsCount;
    Button save,cancel;
    DataUtilities dataUtilities;

    public AddMarketFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddMarketFrag newInstance() {
        return new AddMarketFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_market, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save = view.findViewById(R.id.addMarket_frag_BTN_txt_save);
        cancel =  view.findViewById(R.id.addMarket_frag_BTN_txt_cancel);

        name = view.findViewById(R.id.addMarket_frag_Et_name);
        address = view.findViewById(R.id.addMarket_frag_Et_address);
        phoneNumber = view.findViewById(R.id.addMarket_frag_Et_phone_number);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                nameCharsCount = i2;
                checkDetails();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addressCharsCount= i2;
                checkDetails();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneCharsCount = String.valueOf(editable).length();
                checkDetails();
            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarketDetails marketDetails = new MarketDetails(name.getText().toString(), address.getText().toString(),phoneNumber.getText().toString());
                clearFields();
                dataUtilities = DataUtilities.getInstance(getActivity());
                dataUtilities.openConnect();
                long ins = dataUtilities.insertNewMarket(marketDetails);
                if(ins != -1){
                    Toast.makeText(getActivity(), "the market has been inserted #  "+ins, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "the market already exists", Toast.LENGTH_LONG).show();

                }
              // dataUtilities.close();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }
    public void clearFields(){
        name.setText("");
        address.setText("");
        phoneNumber.setText("");
    }
    public void checkDetails(){
        save.setEnabled((nameCharsCount >= 3) && (addressCharsCount >= 8) && (phoneCharsCount == 15));
    }
}