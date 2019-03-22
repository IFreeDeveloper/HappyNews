package com.example.john.happynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.john.RecyclerList.NewsItem;
import com.example.john.RecyclerList.NewsItemAdapter;
import com.example.john.util.Favorites;
import com.example.john.util.OperateFavorites;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private List<NewsItem> newsItems = new ArrayList<>();
    private NewsItemAdapter adapter;
    private RecyclerView newsList;
    private String UserName;
    private List<Favorites>favorites = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Intent intent = getIntent();
        UserName = intent.getStringExtra("UserName");
        favorites = OperateFavorites.getFavorites(UserName);
        newsList = (RecyclerView)findViewById(R.id.list_favorites);
        newsList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        if(!favorites.isEmpty()) {
            for (int i = 0; i < favorites.size(); i++) {
                Favorites tempFavorites = favorites.get(i);
                NewsItem tempItem = new NewsItem(tempFavorites.getImageURL(), tempFavorites.getNewsURL(), tempFavorites.getTitle(), tempFavorites.getSource(), tempFavorites.getUserName(),tempFavorites.getTime());
                newsItems.add(tempItem);
            }
            adapter = new NewsItemAdapter(newsItems, this, favorites.get(0).getUserName());
            newsList.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            newsList.setLayoutManager(layoutManager);
        }
    }
    public void btnReturn(View view){
        finish();
    }
}
