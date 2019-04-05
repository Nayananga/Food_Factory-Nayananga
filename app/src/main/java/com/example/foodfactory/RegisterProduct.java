package com.example.foodfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterProduct extends AppCompatActivity {
    private EditText editTextProductName;
    private EditText editTextWeight;
    private EditText editTextPrice;
    private EditText editTextDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
    }

    public void saveRegisterProduct(View view) {
        String productName = editTextProductName.getText().toString();
        String weight = editTextWeight.getText().toString();
        String price = editTextPrice.getText().toString();
        String description = editTextDescription.getText().toString();
        // query submit api call;

    }
}
