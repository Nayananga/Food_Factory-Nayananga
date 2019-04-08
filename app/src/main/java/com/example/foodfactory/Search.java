package com.example.foodfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    public DatabaseHandler databaseHandler;
    private EditText searchText;
    private ListView resultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseHandler = new DatabaseHandler(this);
        searchText = findViewById(R.id.editTextSearch);
        resultsList = findViewById(R.id.listViewSearch);
    }

    public void lookUp(View view) {
        if(searchText.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a search keyword", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            final ArrayList<FoodProduct> foodProducts = new ArrayList<>(databaseHandler.listAll("search", searchText.getText().toString()));
            com.example.foodfactory.dataAdapterEditProduct dataAdapterEditProduct = new dataAdapterEditProduct(this, foodProducts);
            resultsList.setAdapter(dataAdapterEditProduct);
        }




    }
}
