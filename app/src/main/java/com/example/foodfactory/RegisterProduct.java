package com.example.foodfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterProduct extends AppCompatActivity {
    private EditText editTextProductName;
    private EditText editTextWeight;
    private EditText editTextPrice;
    private EditText editTextDescription;
    public DatabaseHandler databaseHandler;
    FoodProduct foodProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        foodProduct = new FoodProduct();
        databaseHandler = new DatabaseHandler(this);
    }

    public void saveRegisterProduct(View view) {
        String productName = editTextProductName.getText().toString();
        double weight = Double.valueOf(editTextWeight.getText().toString());
        double price = Double.valueOf(editTextPrice.getText().toString());
        String description = editTextDescription.getText().toString();
        boolean reply;
        foodProduct.setProductName(productName);
        foodProduct.setWeight(weight);
        foodProduct.setPrice(price);
        foodProduct.setDescription(description);
        reply = databaseHandler.insertNewProduct(foodProduct);
        if(reply){
            Toast toast = Toast.makeText(getApplicationContext(), "New Product Saved", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Error occurred while saving", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
