package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
