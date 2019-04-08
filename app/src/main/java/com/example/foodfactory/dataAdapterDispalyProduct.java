package com.example.foodfactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
        CheckBox checkBox;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        FoodProduct data = getItem(position);
        Holder viewHolder;

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_listitem_with_tick_box, parent, false);

            viewHolder.checkBox = convertView.findViewById(R.id.checkBox);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    foodProductsArrayList.get(getPosition).setAvailability(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                }
            });

//            convertView.setTag(viewHolder);
            convertView.setTag(R.id.checkBox, viewHolder.checkBox);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        if (data != null) {
            viewHolder.checkBox.setTag(position); // This line is important
            viewHolder.checkBox.setChecked(foodProductsArrayList.get(position).getAvailability());
            viewHolder.checkBox.setText(data.getProductName());
        }


        return convertView;

    }


}
