package com.example.foodfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Availability extends AppCompatActivity {
    public ArrayList<FoodProduct> foodProductUpdated;
    public DatabaseHandler databaseHandler;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        databaseHandler = new DatabaseHandler(this);
        listView = findViewById(R.id.listViewAvailability);
        ShowRecords();
    }

    public void saveAvailability(View view) {
        int totalUpdateCount = databaseHandler.updateProductAvailability(foodProductUpdated, "availability");
        Toast toast = Toast.makeText(getApplicationContext(), "Updated "+totalUpdateCount+ " Products", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void ShowRecords() {
        final ArrayList<FoodProduct> foodProducts = new ArrayList<>(databaseHandler.listAll("availability"));
        com.example.foodfactory.dataAdapterDispalyProduct dataAdapterDispalyProduct = new dataAdapterDispalyProduct(this, foodProducts);
        listView.setAdapter(dataAdapterDispalyProduct);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox)view.getTag(R.id.checkBox);
                if(!checkBox.isChecked()){
                    checkBox.setChecked(true);
                    Toast toast = Toast.makeText(getApplicationContext(), "Selected "+ foodProducts.get(position).getProductName(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    checkBox.setChecked(false);
                    Toast toast = Toast.makeText(getApplicationContext(), "Removed "+ foodProducts.get(position).getProductName(), Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
        foodProductUpdated = foodProducts;
    }
}
