package com.example.foodfactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Food2Fork {
    private static final String API_URL_BASE = "http://food2fork.com/api/";
    private static final String API_KEY = "4ef178b8b71f1cd460bc85f80f1c1222";
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Performs an HTTP GET and parses the response body as JSON.
     */
    private static JSONObject run(String url) throws IOException, JSONException {
        final Request request = new Request.Builder().url(url).build();
        final Response response = client.newCall(request).execute();
        return new JSONObject(response.body().string());
    }

    public static JSONObject search(String query) throws IOException, JSONException {
        final String url = API_URL_BASE + "/search?key=" + API_KEY + "&q=" + URLEncoder.encode(query, "UTF-8") + "&page=1";
        return run(url);
    }

    /**
     * Extracts recipe IDs from search results.
     */
    public static List<String> getRecipeTitles (JSONObject result) throws JSONException {
        final ArrayList<String> recipeTitles = new ArrayList<>();
        final JSONArray recipes = result.getJSONArray("recipes");
        for (int i = 0; i < recipes.length(); ++i) {
            final JSONObject recipe = recipes.getJSONObject(i);
            final String id = recipe.getString("title");
            recipeTitles.add(id);
        }
        return recipeTitles;
    }

    public static JSONObject getRecipe(String id) throws IOException, JSONException {
        final String url = API_URL_BASE + "get?key=" + API_KEY + "&rId=" + id;
        return run(url);
    }
}
