package com.example.letsbuild;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       if(view==null){
         view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item,viewGroup,false);

       }
      ImageView image= view.findViewById(R.id.product_item_image);
        TextView title= view.findViewById(R.id.product_item_name);
        TextView price= view.findViewById(R.id.product_item_price);

        Product product = products.get(i);

        title.setText(product.getTitle());
        price.setText("LKR "+String.valueOf(product.getPrice())+".00");
        Glide.with(view.getContext()).load(product.getPhotoUri()).into(image);


        return view;
    }
}
