package com.example.foodfactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class dataAdapterEditProduct extends ArrayAdapter<FoodProduct> {

    Context context;
    ArrayList<FoodProduct> foodProductsArrayList;

    public dataAdapterEditProduct (Context context, ArrayList<FoodProduct> foodProductsArrayList) {
        super(context, R.layout.layout_listitem_with_no_tick_box, foodProductsArrayList);
        this.context = context;
        this.foodProductsArrayList = foodProductsArrayList;
    }

    public class EditProductHolder{
        TextView textViewOfProduct;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        FoodProduct data = getItem(position);
        EditProductHolder viewHolder;

        if(convertView == null) {

            viewHolder = new EditProductHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_listitem_with_no_tick_box, parent, false);
            viewHolder.textViewOfProduct = convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
//              convertView.setTag(R.id.textView, viewHolder1.textViewOfProduct);

        }
        else {
            viewHolder = (EditProductHolder) convertView.getTag();
        }

            viewHolder.textViewOfProduct.setTag(position);
            viewHolder.textViewOfProduct.setText(data.getProductName());


       return convertView;
    }

}
