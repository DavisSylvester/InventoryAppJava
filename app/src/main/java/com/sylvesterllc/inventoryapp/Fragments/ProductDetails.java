package com.sylvesterllc.inventoryapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetails extends Fragment {

    Product product;

    public ProductDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle b = getArguments();
        product = b.getParcelable("product");

        Log.d("HELP", product.Name);

        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

}
