package com.sylvesterllc.inventoryapp.DomainClasses;

import java.math.BigDecimal;

public class Product {

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
}
