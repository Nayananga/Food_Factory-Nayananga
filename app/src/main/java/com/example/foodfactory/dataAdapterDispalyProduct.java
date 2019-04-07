package com.example.foodfactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class dataAdapterDispalyProduct extends ArrayAdapter<FoodProduct>{

    Context context;
    ArrayList<FoodProduct> foodProductsArrayList;

    public dataAdapterDispalyProduct (Context context, ArrayList<FoodProduct> foodProductsArrayList){
        super(context, R.layout.layout_listitem_with_tick_box, foodProductsArrayList);
        this.context=context;
        this.foodProductsArrayList = foodProductsArrayList;
    }

    public  class  Holder{
        TextView id;
        CheckBox checkBox;
        TextView productName;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        FoodProduct data = getItem(position);
        Holder viewHolder;

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_listitem_with_tick_box, parent, false);

            viewHolder.id = (TextView) convertView.findViewById(R.id.textViewId);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.textViewProductName);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        if (data != null) {
            viewHolder.id.setText(String.valueOf(data.getId()));
            viewHolder.checkBox.setChecked(data.getAvailability());
            viewHolder.productName.setText(data.getProductName());
        }


        return convertView;

    }


}
