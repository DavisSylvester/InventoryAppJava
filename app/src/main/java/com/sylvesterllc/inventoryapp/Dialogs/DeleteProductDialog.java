package com.sylvesterllc.inventoryapp.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.Interfaces.DeleteDialogListener;
import com.sylvesterllc.inventoryapp.Interfaces.DeleteDialogListener.IDeleteDialogListener;
import com.sylvesterllc.inventoryapp.MainActivity;
import com.sylvesterllc.inventoryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteProductDialog extends DialogFragment
    implements IDeleteDialogListener{

    public DeleteProductDialog() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        builder.setMessage("Delete Product")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                        Log.d("HELP", "You really want to delete this file");

                    }
                })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Log.d("HELP", "You want to cancel");
            }


        });

        return builder.create();
        // return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("HELP", "I received Positive feedback");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

        Log.d("HELP", "I received Negative feedback");
    }
}
