package com.sylvesterllc.inventoryapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sylvesterllc.inventoryapp.Adapters.ProductAdapter;
import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.MainActivity;
import com.sylvesterllc.inventoryapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListing extends Fragment {


    private RecyclerView mRecycleView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    public ProductListing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product_listing, container, false);

        mRecycleView = v.findViewById(R.id.rvProducts);

        setDefaults(getContext());

        return v;
    }

    private void setDefaults(Context ctx) {

        List<Product> data = ((MainActivity)getActivity()).GetData();

        mAdapter = new ProductAdapter(data, ctx);

        mlayoutManager = new LinearLayoutManager(ctx);

        mRecycleView.setAdapter(mAdapter);

        mRecycleView.hasFixedSize();

        mRecycleView.setLayoutManager(mlayoutManager);

    }

}
