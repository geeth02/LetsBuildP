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

public class Account_Adapter extends BaseAdapter {
    private Context context;
    private List<UserInfo> userInfo;

    public Account_Adapter(Context context, List<UserInfo> userInfo) {
        this.context = context;
        this.userInfo = userInfo;
    }

    @Override
    public int getCount() {
        return userInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return userInfo.get(i);
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
        UserInfo product = userInfo.get(i);

        title.setText(product.getName());
        price.setText(product.getNicNumber());
        Glide.with(view.getContext()).load(product.getNicFront()).into(image);

        return view;
    }
}
