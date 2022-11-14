import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsbuild.R;

import java.util.List;

public class Product_Adapter extends BaseAdapter {
    private Context context;
    private List product;


    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int i) {
        return product.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       if(view==null){
           view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);

       }
        ImageView image = view.findViewById(R.id.product_item_image);
       TextView name = view.findViewById(R.id.product_item_name);
        TextView price = view.findViewById(R.id.product_item_price);


        return view;
    }
}
