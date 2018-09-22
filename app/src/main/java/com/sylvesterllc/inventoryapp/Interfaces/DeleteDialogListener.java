package com.sylvesterllc.inventoryapp.Interfaces;

import android.app.Activity;
import android.app.DialogFragment;
import android.util.Log;
import android.widget.ExpandableListView;

import com.sylvesterllc.inventoryapp.Dialogs.DeleteProductDialog;

public class DeleteDialogListener  extends DialogFragment {

    public interface IDeleteDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    DeleteDialogListener.IDeleteDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DeleteDialogListener.IDeleteDialogListener) activity;

            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DeleteDialogListener.IDeleteDialogListener");
        }
        catch(Exception ex) {

        }
    }


    public void onDialogPositiveClick(DialogFragment dialog){
        Log.d("HELP", "Clicked in Postive Clicked");


    }

}

