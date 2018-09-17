package com.sylvesterllc.inventoryapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sylvesterllc.inventoryapp.MainActivity;
import com.sylvesterllc.inventoryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavigation extends Fragment {

    BottomNavigationView navMenu;


    public BottomNavigation() {
        // Required empty public constructor
    }

    private void addMenuListener(BottomNavigationView menu) {

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_add_inventory:
                        AddInventory ai = new AddInventory();
                        ((MainActivity) getActivity()).startFragment(ai);
                        return true;

                    case R.id.nav_product_list:
                        ProductListing pl = new ProductListing();
                        ((MainActivity) getActivity()).startFragment(pl);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        navMenu = v.findViewById(R.id.bottomNav);

        addMenuListener(navMenu);
        return v;
    }

}
