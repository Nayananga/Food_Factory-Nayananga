package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class Recipes extends AppCompatActivity {
    public ArrayList<FoodProduct> foodProductUpdated;
    public DatabaseHandler databaseHandler;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        databaseHandler = new DatabaseHandler(this);
        listView = findViewById(R.id.listViewRecipe);
        ShowRecords();
    }

    public void findRecipes(View view) {
//        foodProductUpdated.trimToSize();
        StringBuilder searchText = new StringBuilder();
        Bundle extras = new Bundle();
        Iterator<FoodProduct> itr = foodProductUpdated.iterator();
        while (itr.hasNext()){
            FoodProduct temp = itr.next();
            if(temp.getAvailability()){
                searchText.append(temp.getProductName()).append(",");
            }
        }
        extras.putString("SEARCHTEXT", String.valueOf(searchText));
        Intent findRecipes = new Intent(Recipes.this,FindRecipies.class);
        findRecipes.putExtras(extras);
        startActivity(findRecipes);

    }

    private void ShowRecords() {
        final ArrayList<FoodProduct> foodProducts = new ArrayList<>(databaseHandler.listAll("availability"));
        com.example.foodfactory.dataAdapterDispalyProduct dataAdapterDispalyProduct = new dataAdapterDispalyProduct(this, foodProducts);
        foodProductUpdated = foodProducts;
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
