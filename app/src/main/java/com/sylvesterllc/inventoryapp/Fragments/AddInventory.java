package com.sylvesterllc.inventoryapp.Fragments;


import android.content.res.Resources;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddInventory extends Fragment {



    public AddInventory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_inventory, container, false);


        return view;
    }





}
