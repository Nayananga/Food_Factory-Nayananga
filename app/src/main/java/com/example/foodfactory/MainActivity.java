package com.example.foodfactory;

import android.app.ProgressDialog;
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
    private String url = "https://www.food2fork.com/api/search?key=YOUR_API_KEY&q=chicken,oregano&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        new GetJSONTask().execute(url);
    }

    private class GetJSONTask extends AsyncTask<String, Void, String> {
        private ProgressDialog pd;

        // onPreExecute called before the doInBackgroud start for display
        // progress dialog.
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd = ProgressDialog.show(MainActivity.this, "", "Loading", true,
//                    false); // Create and show Progress dialog
//        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                return Utility.downloadDataFromUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }
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
