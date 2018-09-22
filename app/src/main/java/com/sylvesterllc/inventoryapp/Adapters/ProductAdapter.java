package com.sylvesterllc.inventoryapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.sylvesterllc.inventoryapp.DomainClasses.Product;
import com.sylvesterllc.inventoryapp.Fragments.ProductDetails;
import com.sylvesterllc.inventoryapp.MainActivity;
import com.sylvesterllc.inventoryapp.R;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> data;
    private Context context;
    private MainActivity mainActivity;


    public ProductAdapter(List<Product> products, Context ctx, MainActivity ma)  {
        data = products;
        context = ctx;
        mainActivity = ma;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);

        ViewHolder vh = new ProductAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.bindProduct(data.get(position), context, mainActivity);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPrice;
        TextView txtQty;
        ImageButton btnBuy;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQty = itemView.findViewById(R.id.txtQty);
            btnBuy = itemView.findViewById(R.id.imgBtnBuy);

        }

        public void bindProduct(final Product p, final Context context, final MainActivity mainActivity) {

           txtName.setText(p.Name);
           txtPrice.setText("$" + p.Price);
           txtQty.setText("Qty: " + p.Qty);

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HELP", "Qty should be " + p.Qty + " - 1 = " + (p.Qty - 1));

                    if (p.Qty == 0) {
                        Toast.makeText(context, "We are currently Out of Stock", Toast.LENGTH_LONG).show();
                    } else {

                        p.Qty = p.Qty - 1;

                        txtQty.setText("Qty: " + p.Qty);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ProductDetails pd = new ProductDetails();
                    Bundle b = new Bundle();
                    b.putParcelable("product", p);
                    pd.setArguments(b);
                    mainActivity.startFragment(pd);
                }
            });

        }
    }



}
