package com.sylvesterllc.inventoryapp.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.MainActivity;
import com.sylvesterllc.inventoryapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetails extends Fragment {

    Product product;
    EditText etProductName;
    EditText etProductPrice;
    EditText etProductQty;
    EditText etProductSupplierName;
    EditText etProductSupplierPhone;
    Button btnDelete;
    Button btnSaveProduct;
    ImageView btnCall;
    ImageView btnQtyAdd;
    ImageView btnQtyMinus;


    String originalProductName;

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

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        setDefault(view);

        return view;
    }

    private void setDefault(View v) {

        etProductName = v.findViewById(R.id.txtProductName);
        etProductPrice = v.findViewById(R.id.txtPrice);
        etProductQty = v.findViewById(R.id.txtQty);
        etProductSupplierName = v.findViewById(R.id.txtSupplierName);
        etProductSupplierPhone = v.findViewById(R.id.txtSupplierPhone);

        etProductName.setText(product.Name);
        etProductPrice.setText("$" + product.Price);
        etProductQty.setText(String.valueOf(product.Qty));
        etProductSupplierName.setText(product.SupplierName);
        etProductSupplierPhone.setText(product.SupplierPhone);

        btnDelete = v.findViewById(R.id.btnDelete);
        btnCall = v.findViewById(R.id.btnCall);
        btnQtyAdd = v.findViewById(R.id.btnQtyIncrease);
        btnQtyMinus = v.findViewById(R.id.btnQtyDecrease);
        btnSaveProduct = v.findViewById(R.id.btnSave);

        originalProductName = etProductName.getText().toString();


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HELP", "You clicked the Delete Button");

                MainActivity ma =  (MainActivity) getActivity();

                showDialog(product, ma);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int hasWriteContactsPermission = getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE);

                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                123);
                        return;
                    }

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + product.SupplierPhone));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + product.SupplierPhone));
                    startActivity(intent);
                }
            }
        });

        btnQtyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.Qty = product.Qty + 1;
                etProductQty.setText(String.valueOf(product.Qty));
            }
        });

        btnQtyMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.Qty != 0) {
                    product.Qty = product.Qty - 1;
                    etProductQty.setText(String.valueOf(product.Qty));
                } else {
                    Toast.makeText(getContext(), R.string.out_of_stock_text, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity ma = ((MainActivity) getActivity());

                setProductValuesBeforeSave();

                if (ma != null) {
                    List<String> errors = ma.Validated(product.Name,
                           Integer.toString(product.Price), Integer.toString(product.Qty),
                            product.SupplierName, product.SupplierPhone);

                    Boolean hasErrors = errors.size() == 0 ? false : true;


                    if (hasErrors) {
                        String errorResult = "";

                        for (int i = 0; i < errors.size(); i++) {
                            errorResult += errors.get(i) + "\n";
                        }

                        Toast.makeText(getContext(), errorResult, Toast.LENGTH_LONG).show();
                        return;
                    }

                    ma.UpdateData(originalProductName, product);

                    Log.d("HELP", "So you want to save a Product huh");
                }
            }
        });

    }

    private void setProductValuesBeforeSave() {
        product.Name = etProductName.getText().toString();
        product.Price = Integer.parseInt(etProductPrice.getText().toString());
        product.Qty = Integer.parseInt(etProductQty.getText().toString());
        product.SupplierName = etProductSupplierName.getText().toString();
        product.SupplierPhone = etProductSupplierPhone.getText().toString();
    }

    private void showDialog(final Product product, final MainActivity ma) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("HELP", "You really want to delete product: " + product.Name);

                        ma.DeleteProduct(product.Name);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("HELP", "You want to cancel");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + product.SupplierPhone));
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
