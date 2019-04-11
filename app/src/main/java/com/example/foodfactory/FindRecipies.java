package com.example.foodfactory;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.foodfactory.Food2Fork.getRecipeTitles;

public class FindRecipies extends AppCompatActivity {
    private Food2Fork food2Fork;
    private String PRODUCTNAME;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipies);
        listView = findViewById(R.id.listViewFindRecipies);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        PRODUCTNAME = extras.getString("SEARCHTEXT");
        enableStrictMode();
        food2Fork = new Food2Fork();
        ShowRecords();
    }

    public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    private void ShowRecords(){
        try {
            final JSONObject searchResults = food2Fork.search(PRODUCTNAME);
            // Get and print the first recipe.
            final ArrayList<String>  recipes = new ArrayList<>(getRecipeTitles(searchResults));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.layout_listitem_with_no_tick_box, R.id.textView, recipes);
            listView.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
