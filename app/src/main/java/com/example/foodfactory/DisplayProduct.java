package com.example.foodfactory;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayProduct extends AppCompatActivity {
    public FoodProduct foodProduct;
    public DatabaseHandler databaseHandler;
    private dataAdapterDispalyProduct dataAdapterDispalyProduct;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);
        databaseHandler = new DatabaseHandler(this);
        listView = findViewById(R.id.listViewDisplayProduct);
        ShowRecords();
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_1,
//                (Cursor) databaseHandler.listAll("displayProduct"),
//                new String[] { "_id", "product_name" , "availability" },
//                new int[] {R.id.textViewId,R.id.checkBox,R.id.textViewProductName});
//        listView.setAdapter(simpleCursorAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // When clicked, show a toast with the TextView text
////                setToast("Answer: " + ((TextView) view).getText());
//                Toast toast = Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });

    }

    public void addToKitchen(View view) {
    }

    private void ShowRecords(){
        final ArrayList<FoodProduct> foodProducts = new ArrayList<>(databaseHandler.listAll("dispalyProduct"));
        dataAdapterDispalyProduct = new dataAdapterDispalyProduct(this,foodProducts);
        listView.setAdapter(dataAdapterDispalyProduct);

    }
}
