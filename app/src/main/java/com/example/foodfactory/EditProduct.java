package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProduct extends AppCompatActivity {
    public DatabaseHandler databaseHandler;
    private ListView listView;
    private FoodProduct dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        databaseHandler = new DatabaseHandler(this);
        listView = findViewById(R.id.listViewEditProduct);
        ShowRecords();
    }

    private void ShowRecords(){
        final ArrayList<FoodProduct> foodProducts = new ArrayList<>(databaseHandler.listAll("dispalyProduct"));
        com.example.foodfactory.dataAdapterEditProduct dataAdapterEditProduct = new dataAdapterEditProduct(this, foodProducts);
        listView.setAdapter(dataAdapterEditProduct);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataModel = foodProducts.get(position);
                Bundle extras = new Bundle();
                extras.putLong("PRODUCTID",dataModel.getId());
                extras.putString("PRODUCTNAME", dataModel.getProductName());
                extras.putDouble("PRODUCTWEIGHT", dataModel.getWeight());
                extras.putDouble("PRODUCTPRICE", dataModel.getPrice());
                extras.putString("PRODUCTDESCRIPTION", dataModel.getDescription());
                extras.putBoolean("PRODUCTAVAILABILITY", dataModel.getAvailability());
                Intent editProduct = new Intent(EditProduct.this,UpdateProduct.class);
                editProduct.putExtras(extras);
                startActivity(editProduct);

            }
        });
    }

}
