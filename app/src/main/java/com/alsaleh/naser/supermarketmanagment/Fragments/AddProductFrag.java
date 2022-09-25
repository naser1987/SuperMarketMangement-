package com.alsaleh.naser.supermarketmanagment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.alsaleh.naser.supermarketmanagment.ProductDetails;
import com.alsaleh.naser.supermarketmanagment.R;

import java.util.ArrayList;

public class AddProductFrag extends Fragment {
    Button save, cancel;
    EditText code, name, supplier, productionDate, expireDate;
    DataUtilities dataUtilities;
    ArrayList<ProductDetails> Products;

    public AddProductFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddProductFrag newInstance() {
        return new AddProductFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateView(view);
    }

    //inflate the view //
    public void inflateView(View view){
        save = view.findViewById(R.id.Add_Product_Frag_BTN_id_save);
        cancel = view.findViewById(R.id.Add_Product_Frag_BTN_id_cancel);
        code = view.findViewById(R.id.Add_Product_Frag_ET_id_code);
        name = view.findViewById(R.id.Add_Product_Frag_ET_id_name);
        supplier = view.findViewById(R.id.Add_Product_Frag_ET_id_supplier);
        productionDate = view.findViewById(R.id.Add_Product_Frag_ET_id_productionDate);
        expireDate = view.findViewById(R.id.Add_Product_Frag_ET_id_expireDate);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUtilities = DataUtilities.getInstance(getActivity());
                dataUtilities.openConnect();
                Products = dataUtilities.getAllProducts();
                int i =0;
                for(ProductDetails productDetails:Products) {
                    Log.d(i +" ", productDetails.getName()+" " +productDetails.getCode());
                    i++;
                }
               // clearFields();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( ((name.getText().toString().length() ) >= 4 ) && ( (code.getText().toString().length())  >=14 ) && ( (supplier.getText().toString().length()) >= 4 ) && checkDates(productionDate.getText().toString(),expireDate.getText().toString())) {
                    dataUtilities = DataUtilities.getInstance(getActivity());
                    dataUtilities.openConnect();
                    long res = dataUtilities.insertNewProduct(CollapseProductionDetails());
                    if (res != -1) {
                        Log.d("product #", res+"");

                        clearFields();
                    } else {
                        Toast.makeText(getActivity(), " product already exists", Toast.LENGTH_SHORT).show();
                    }
                //    dataUtilities.close();
                }else{
                    Toast.makeText(getActivity(),"the details  are not correct, please check  the product's details",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    // check the date is correct or not //
    public boolean checkDates(String prodDate, String expDate) {
        long prodYear , expYear ;
        int prodMonth  , expMonth  , prodDay , expDay;

        String[] date1 = prodDate.split("/");
        String[] date2 = expDate.split("/");
        if(checkFormatDate(prodDate) && checkFormatDate(expDate)){
            prodDay = Integer.parseInt(date1[0]);
            prodMonth = Integer.parseInt(date1[1]);
            prodYear = Long.parseLong(date1[2]);
            expDay = Integer.parseInt(date2[0]);
            expMonth = Integer.parseInt(date2[1]);
            expYear = Long.parseLong(date2[2]);
            if (expYear > prodYear ) {
                return (checkMonthDay(prodMonth, prodDay)&& checkMonthDay(expMonth, expDay));
            }else if (expYear == prodYear){
                return ( (expMonth > prodMonth)&& (checkMonthDay(prodMonth, prodDay)&& checkMonthDay(expMonth, expDay)) ) ;
            }
        }
        // date is not valid
        return false;
    }

    // check the month with his days are correct or not //
    public boolean checkMonthDay(int month,int day ){
        if(month > 0 && month <= 12) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return day > 0 && day <= 31;

                case 2:
                    return day > 0 && day <= 28;
                case 4:
                case 6:
                case 9:
                case 11:
                    return day > 0 && day <= 30;
            }
        }

        return false;
    }

    // clear the fields //
    public void clearFields(){
        name.setText("");
        code.setText("");
        supplier.setText("");
        productionDate.setText("");
        expireDate.setText("");
    }

    // collect the details of the product to insert it in the database//
    public ProductDetails CollapseProductionDetails(){
        ProductDetails prod = new ProductDetails();
        prod.setCode(code.getText().toString());
        prod.setName(name.getText().toString());
        prod.setSupplier(supplier.getText().toString());
        prod.setProdDate(productionDate.getText().toString());
        prod.setExpireDate(expireDate.getText().toString());
        return prod;
    }

    // check the format of the date is correct or not //
    public boolean checkFormatDate(String date){

            String[] dates = date.split("/");
        // the date format is  valid or not //
        return ( ( dates[0].length() == 2 )  && ( dates[1].length() == 2 ) && ( dates[2].length() == 4 ) );
    }

}