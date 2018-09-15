package com.sylvesterllc.inventoryapp.DomainClasses;

import android.provider.BaseColumns;

public class InventoryContract {

    public InventoryContract() {

    }

    public class InventoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "Inventory";

        public static final String COL_PRODUCT_NAME = "Name";
        public static final String COL_PRICE = "Price";
        public static final String COL_QTY = "Qty";
        public static final String COL_SUPPLIER_NAME = "Supplier";
        public static final String COL_SUPPLIER_PHONE = "SupplierPhone";


    }


}
