package com.sylvesterllc.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sylvesterllc.inventoryapp.DomainClasses.InventoryContract;
import com.sylvesterllc.inventoryapp.Helpers.InventoryDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InventoryDBHelper helper  = new InventoryDBHelper(getBaseContext());

        SQLiteDatabase db = helper.getWritableDatabase();

        AddData(db);

        GetData(db);

        Log.d("HELP", GetData(db));

    }

    private Long AddData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_NAME, "VENUM CHALLENGER 2.0 BOXING GLOVES - WHITE/GOLD");
        values.put(InventoryContract.InventoryEntry.COL_PRICE, 49.99);
        values.put(InventoryContract.InventoryEntry.COL_QTY, 2);
        values.put(InventoryContract.InventoryEntry.COL_SUPPLIER_NAME, "VENUM ");
        values.put(InventoryContract.InventoryEntry.COL_SUPPLIER_PHONE, "1 (888) 240-6653");


        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        Log.d("HELP", Long.toString(newRowId));

        return newRowId;

    }

    private String GetData(SQLiteDatabase db) {

        String[] projection = {
                BaseColumns._ID,
                InventoryContract.InventoryEntry.COL_PRODUCT_NAME
        };

        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        List itemIds = new ArrayList<>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();

        if (itemIds.size() == 0) {
            return "No Records";
        }

        return itemIds.get(itemIds.size() - 1).toString();
    }

}
