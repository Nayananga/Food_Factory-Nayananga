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
        if(editTextProductName.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product name", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(editTextWeight.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product Weight", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(editTextPrice.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product price", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String productName = editTextProductName.getText().toString();
            foodProduct.setProductName(productName);
            double weight = Double.valueOf(editTextWeight.getText().toString());
            foodProduct.setWeight(weight);
            double price = Double.valueOf(editTextPrice.getText().toString());
            foodProduct.setPrice(price);
            String description = editTextDescription.getText().toString();
            foodProduct.setDescription(description);

            boolean reply;
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
}
