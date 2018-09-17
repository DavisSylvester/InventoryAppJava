package com.sylvesterllc.inventoryapp.DomainClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Product implements Parcelable {

    public String Name;
    public Integer Price;
    public Integer Qty;
    public String SupplierName;
    public String SupplierPhone;


    public Product(String name, Integer price, Integer qty, String sName,
                   String sPhone) {
        Name = name;
        Price = price;
        Qty = qty;
        SupplierName = sName;
        SupplierPhone = sPhone;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(Name);
        parcel.writeInt(Price);
        parcel.writeInt(Qty);
        parcel.writeString(SupplierName);
        parcel.writeString(SupplierPhone);

    }
}
