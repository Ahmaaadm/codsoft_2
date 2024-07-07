package com.example.quotesapp_codsoft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView quotesTextView;
    private Button refreshButton, shareButton, saveButton,favoriteButton;
    private String currentQuote;
    private String[] quotes;
    private SharedPreferences sharedPreferences;
    private static final String FAVORITES_KEY = "favorite_quotes";
    private static final String SHARED_PREF_NAME = "FavoriteQuotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotes = new String[]{
                "The best way to predict the future is to invent it.",
                "Do not wait for the perfect time and place to enter, for you are already onstage.",
                "The only way to do great work is to love what you do.",
                "The harder you work for something, the greater you'll feel when you achieve it.",
                "Success is not the key to happiness. Happiness is the key to success.",
                "Don’t watch the clock; do what it does. Keep going.",
                "Action is the foundational key to all success.",
                "Opportunities don't happen, you create them.",
                "It always seems impossible until it's done.",
                "Don’t let yesterday take up too much of today.",
                "Failure will never overtake me if my determination to succeed is strong enough.",
                "Knowing is not enough; we must apply. Wishing is not enough; we must do.",
                "Believe you can and you’re halfway there.",
                "Start where you are. Use what you have. Do what you can.",
                "Your time is limited, don't waste it living someone else's life.",
                "The best revenge is massive success.",
                "Dream big and dare to fail.",
                "To see what is right and not do it is a lack of courage.",
                "If you want to lift yourself up, lift up someone else.",
                "The only limit to our realization of tomorrow is our doubts of today.",
                "Success usually comes to those who are too busy to be looking for it.",
                "Don’t be afraid to give up the good to go for the great.",
                "Success is walking from failure to failure with no loss of enthusiasm.",
                "The way to get started is to quit talking and begin doing.",
                "Success is not how high you have climbed, but how you make a positive difference to the world.",
                "It’s not whether you get knocked down; it’s whether you get up.",
                "Perseverance is not a long race; it is many short races one after the other.",
                "Failure is not the opposite of success; it’s part of success.",
                "You just can’t beat the person who never gives up.",
                "The only place where success comes before work is in the dictionary.",
                "Life is 10% what happens to us and 90% how we react to it.",
                "Change is the end result of all true learning.",
                "Develop a passion for learning. If you do, you will never cease to grow.",
                "Don’t limit your challenges. Challenge your limits.",
                "If you're not willing to risk the usual, you will have to settle for the ordinary.",
                "Innovation distinguishes between a leader and a follower.",
                "Creativity is intelligence having fun.",
                "You can’t use up creativity. The more you use, the more you have.",
                "The best ideas come as jokes. Make your thinking as funny as possible.",
                "Your imagination is your preview of life’s coming attractions.",
                "The art of leadership is saying no, not saying yes. It is very easy to say yes.",
                "Leadership is the capacity to translate vision into reality.",
                "The function of leadership is to produce more leaders, not more followers.",
                "A leader is one who knows the way, goes the way, and shows the way.",
                "True leadership lies in guiding others to success—in ensuring that everyone is performing at their best, doing the work they are pledged to do and doing it well.",
                "Keep your face always toward the sunshine—and shadows will fall behind you.",
                "Your attitude, not your aptitude, will determine your altitude.",
                "The only limit to our realization of tomorrow is our doubts of today.",
                "Positive thinking will let you do everything better than negative thinking will.",
                "What lies behind us and what lies before us are tiny matters compared to what lies within us."
        };


        quotesTextView = findViewById(R.id.quoteTextView);
        refreshButton = findViewById(R.id.refreshButton);
        shareButton = findViewById(R.id.shareButton);
        saveButton = findViewById(R.id.saveButton);
        favoriteButton = findViewById(R.id.favoriteButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindQuote();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuote();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, activity_favorite.class);
                startActivity(intent);
            }
        });

        bindQuote();
    }

    private void bindQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        currentQuote = quotes[index];
        quotesTextView.setText(currentQuote);
    }

    private void shareQuote() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void saveQuote() {
        // Retrieve current favorite quotes as a new HashSet to ensure it's mutable
        Set<String> favorites = new HashSet<>(sharedPreferences.getStringSet(FAVORITES_KEY, new HashSet<>()));

        // Add the current quote
        favorites.add(currentQuote);

        // Save the updated set of quotes
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(FAVORITES_KEY, favorites);
        editor.apply();

        Toast.makeText(this, "Quote saved", Toast.LENGTH_SHORT).show();

        Log.d("MainActivity", "Favorites size: " + favorites.size());
    }

}
