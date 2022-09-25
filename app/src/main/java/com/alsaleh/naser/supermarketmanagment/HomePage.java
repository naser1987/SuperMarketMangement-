package com.alsaleh.naser.supermarketmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.FrameLayout;


import com.alsaleh.naser.supermarketmanagment.Fragments.AddMarketFrag;
import com.alsaleh.naser.supermarketmanagment.Fragments.AddProductFrag;
import com.alsaleh.naser.supermarketmanagment.Fragments.AddProductToMarketFrag;
import com.alsaleh.naser.supermarketmanagment.Fragments.FindPriceFrag;
import com.alsaleh.naser.supermarketmanagment.Fragments.SearchProductFrag;
import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;
import com.google.android.material.tabs.TabLayout;



public class HomePage extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout container;
    DataUtilities dataUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tabLayout = findViewById(R.id.home_tablayout);
        container = findViewById(R.id.container);
        dataUtility = DataUtilities.getInstance(HomePage.this);
        dataUtility.openConnect();

        setFragment(AddMarketFrag.newInstance());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch(tab.getPosition()){
                    case 0:
                       fragment = AddMarketFrag.newInstance();
                        setFragment(fragment);

                        break;
                    case 1:
                        fragment = AddProductFrag.newInstance();
                        setFragment(fragment);

                        break;
                    case 2:
                        fragment = AddProductToMarketFrag.newInstance();
                        setFragment(fragment);

                        break;
                    case 3:
                        fragment = SearchProductFrag.newInstance();
                        setFragment(fragment);
                        break;
                    default:
                        fragment = FindPriceFrag.newInstance();
                        setFragment(fragment);
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }
    public void setFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,frag);
        ft.commit();
    }

}