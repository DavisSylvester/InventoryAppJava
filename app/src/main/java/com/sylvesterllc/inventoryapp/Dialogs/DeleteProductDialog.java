package com.sylvesterllc.inventoryapp.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sylvesterllc.inventoryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteProductDialog extends DialogFragment {


    public DeleteProductDialog() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Delete Product")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
        // return super.onCreateDialog(savedInstanceState);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_delete_product_dialog, container, false);
//    }

}
