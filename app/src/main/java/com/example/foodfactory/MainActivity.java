package com.example.foodfactory;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
    }

    public void registerProduct(View view) {
        Intent intent = new Intent(this, RegisterProduct.class);
        startActivity(intent);
    }

    public void displayProduct(View view) {
        Intent intent = new Intent(this, DisplayProduct.class);
        startActivity(intent);
    }

    public void availability(View view) {
        Intent intent = new Intent(this, Availability.class);
        startActivity(intent);
    }

    public void editProduct(View view) {
        Intent intent = new Intent(this, EditProduct.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void recipes(View view) {
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }
}
