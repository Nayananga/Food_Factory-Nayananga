package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateProduct extends AppCompatActivity {
    private EditText productName;
    private EditText productWeight;
    private EditText productPrice;
    private EditText productDescription;
    private CheckBox productAvailability;
    private FoodProduct updatedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        productName = findViewById(R.id.editTextProductName);
        productWeight = findViewById(R.id.editTextWeight);
        productPrice = findViewById(R.id.editTextPrice);
        productDescription = findViewById(R.id.editTextDescription);
        productAvailability = findViewById(R.id.checkBoxUpdateAvailability);
        updatedProduct = new FoodProduct();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            updatedProduct = getExtras(extras);
        }


    }

    private FoodProduct getExtras (Bundle extras){
        Long PRODUCTID = extras.getLong("PRODUCTID");
        String PRODUCTNAME = extras.getString("PRODUCTNAME");
        Double PRODUCTWEIGHT = extras.getDouble("PRODUCTWEIGHT");
        Double PRODUCTPRICE = extras.getDouble("PRODUCTPRICE");
        String PRODUCTDESCRIPTION = extras.getString("PRODUCTDESCRIPTION");
        productName.setText(PRODUCTNAME);
        productWeight.setText(String.valueOf(PRODUCTWEIGHT));
        productPrice.setText(String.valueOf(PRODUCTPRICE));
        productDescription.setText(PRODUCTDESCRIPTION);
        updatedProduct.setId(PRODUCTID);
        return updatedProduct;
    }

    public void saveUpdateProduct(View view) {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        if(productName.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product name", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(productWeight.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product Weight", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(productPrice.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill Product price", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            updatedProduct.setProductName(productName.getText().toString());
            updatedProduct.setWeight(Double.valueOf(productWeight.getText().toString()));
            updatedProduct.setPrice(Double.valueOf(productPrice.getText().toString()));
            updatedProduct.setDescription(productDescription.getText().toString());
            updatedProduct.setAvailability(productAvailability.isChecked());
            boolean updateCount = databaseHandler.updateProduct(updatedProduct);
            if(updateCount){
                Toast toast = Toast.makeText(getApplicationContext(), "Product Updated Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }
}
