package com.sylvesterllc.inventoryapp.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sylvesterllc.inventoryapp.DomainClasses.InventoryContract;

public class InventoryDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inventory.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " (" +
                    InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY," +
                    InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " TEXT," +
                    InventoryContract.InventoryEntry.COL_PRICE + " REAL, " +
                    InventoryContract.InventoryEntry.COL_QTY + " INTEGER, " +
                    InventoryContract.InventoryEntry.COL_SUPPLIER_NAME + " TEXT, " +
                    InventoryContract.InventoryEntry.COL_SUPPLIER_PHONE + " TEXT " +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + InventoryContract.InventoryEntry.TABLE_NAME;

    public InventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
