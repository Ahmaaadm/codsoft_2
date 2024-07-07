package com.example.quotesapp_codsoft;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class activity_favorite extends AppCompatActivity {

    private ListView listView;
    private List<String> favoriteQuotes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        listView = findViewById(R.id.listView);

        favoriteQuotes = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteQuotes", MODE_PRIVATE);
        Set<String> favoritesSet = sharedPreferences.getStringSet("favorite_quotes", null);

        if (favoritesSet != null) {
            favoriteQuotes.addAll(favoritesSet);
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, R.id.textViewQuote, favoriteQuotes);

        listView.setAdapter(adapter);
    }
}
