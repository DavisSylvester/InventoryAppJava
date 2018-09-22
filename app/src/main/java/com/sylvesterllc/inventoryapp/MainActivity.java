package com.sylvesterllc.inventoryapp;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sylvesterllc.inventoryapp.DomainClasses.InventoryContract;
import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.Fragments.ProductListing;
import com.sylvesterllc.inventoryapp.Helpers.InventoryDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager gls;
    SQLiteDatabase db;
    TextView noData;
    boolean cleanDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gls = getSupportFragmentManager();
        noData = findViewById(R.id.txtNoData);

        InventoryDBHelper helper  = new InventoryDBHelper(getBaseContext());

        db = helper.getWritableDatabase();

        DeleteOldData();

        if (hasProducts()) {
            noData.setVisibility(View.GONE);
            startFragment(new ProductListing());
        }
        else {
            noData.setVisibility(View.VISIBLE);
        }

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

    private Long AddProduct(Product p) {

        ContentValues values = GenerateContentValues(p);

        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        Log.d("HELP", Long.toString(newRowId));

        return newRowId;

    }

    public List<Product> GetData() {

        String[] projection = {
                BaseColumns._ID,
                InventoryContract.InventoryEntry.COL_PRODUCT_NAME,
                InventoryContract.InventoryEntry.COL_PRICE,
                InventoryContract.InventoryEntry.COL_QTY,
                InventoryContract.InventoryEntry.COL_SUPPLIER_NAME,
                InventoryContract.InventoryEntry.COL_SUPPLIER_PHONE
        };

        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        List result = new ArrayList<Product>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry._ID));

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COL_PRODUCT_NAME));

            int price = cursor.getInt(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COL_PRICE));

            int qty = cursor.getInt(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COL_QTY));

            String sName = cursor.getString(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COL_SUPPLIER_NAME));

            String sPhone = cursor.getString(
                    cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COL_SUPPLIER_PHONE));

            result.add(new Product(name, price, qty, sName, sPhone));
        }
        cursor.close();

        return result;
    }

    public void DeleteOldData() {

        if (cleanDatabase) {
            String projection =
                    InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " Like ?";

            String[] selectionArgs = {"%Venum%"};

            int resultCount = db.delete(
                    InventoryContract.InventoryEntry.TABLE_NAME,
                    projection,
                    selectionArgs);

            Log.d("HELP", "Deleted " + Integer.toString(resultCount));

            AddData(db);
        }
    }

    public void DeleteProduct(String productName) {

        String projection =
                InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " = ?";

        String[] selectionArgs = { productName };

        int resultCount = db.delete(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                selectionArgs);


        Log.d("HELP", "Deleted " + Integer.toString(resultCount));
    }

    public void UpdateData(String productName, Product p) {

        ContentValues values = GenerateContentValues(p);

        String where = InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " = ?";

        String[] whereArgs = { productName };

        db.update(InventoryContract.InventoryEntry.TABLE_NAME,
                values,
                where,
                whereArgs
                );
    }

    public <T extends Fragment>  void startFragment(T frag) {

        noData.setVisibility(View.GONE);

        gls.beginTransaction()
                .replace(R.id.csMainContent, frag)
                .commit();
    }

    public void Save(View v) {

        EditText name = findViewById(R.id.txtProductName);
        EditText price = findViewById(R.id.txtProductPrice);
        EditText qty = findViewById(R.id.txtProductQty);
        EditText supplierName = findViewById(R.id.txtProductSupplierName);
        EditText supplierPhone = findViewById(R.id.txtProductSupplierPhone);



        List<String> errors = Validated(name.getText().toString(),
                price.getText().toString(),
                qty.getText().toString(),
                supplierName.getText().toString(),
                supplierPhone.getText().toString());

        Boolean hasErrors = errors.size() == 0 ? false : true;


        if (hasErrors) {
            String errorResult = "";

            for (int i = 0; i < errors.size(); i++) {
                errorResult += errors.get(i) + "\n";
            }

            Toast.makeText(this, errorResult, Toast.LENGTH_LONG).show();
            return;
        }
        else {

            // Makes sure price doesn't access decimal

            String priceChecker = (price.getText().toString().contains(".")) ? price.getText().toString().split(".")[0] : price.getText().toString();

            Product product = new Product(
                    name.getText().toString(),
                    Integer.parseInt(priceChecker),
                    Integer.parseInt(qty.getText().toString()),
                    supplierName.getText().toString(),
                    supplierPhone.getText().toString());

            AddProduct(product);

            ClearForm(name, price, qty, supplierName, supplierPhone);
        }
    }

    private ContentValues GenerateContentValues(Product p) {

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_NAME, p.Name);
        values.put(InventoryContract.InventoryEntry.COL_PRICE, p.Price);
        values.put(InventoryContract.InventoryEntry.COL_QTY, p.Qty);
        values.put(InventoryContract.InventoryEntry.COL_SUPPLIER_NAME, p.SupplierName);
        values.put(InventoryContract.InventoryEntry.COL_SUPPLIER_PHONE, p.SupplierPhone);

        return values;
    }

    public List<String> Validated(String name, String price, String qty, String sName,
                                  String sPhone) {

        List<String> validationErrors = new ArrayList();

        if (name.length() == 0) {
            validationErrors.add(getApplicationContext().getResources().getString(R.string.no_product_name));

        }

        if (price.length() == 0) {
            validationErrors.add(getApplicationContext().getResources().getString(R.string.no_product_price));
        }

        if (qty.length() == 0) {
            validationErrors.add(getApplicationContext().getResources().getString(R.string.no_product_qty));
        }

        if (sName.length() == 0) {
            validationErrors.add(getApplicationContext().getResources().getString(R.string.no_product_supplierName));
        }

        if (sPhone.length() == 0) {
            validationErrors.add(getApplicationContext().getResources().getString(R.string.no_product_supplierPhone));
        }

        return validationErrors;

    }

    private void ClearForm(EditText name, EditText price, EditText qty, EditText sName,
                           EditText sPhone) {

        name.setText("");
        price.setText("");
        qty.setText("");
        sName.setText("");
        sPhone.setText("");
    }

    private boolean hasProducts() {

        Cursor cursor = db.rawQuery("SELECT Count(*) FROM " +
                InventoryContract.InventoryEntry.TABLE_NAME + ";", null);

        cursor.moveToFirst();

        int totalRows = cursor.getInt(0);

        Log.d("HELP", "Total Rows: " + totalRows);

        return (totalRows == 0) ? false : true;

    }

}
