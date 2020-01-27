package com.example.swiveltest;

import android.content.Intent;
import android.os.Bundle;

import com.example.swiveltest.CardModel.CardModel;
import com.example.swiveltest.DTO.MovieDTO;
import com.example.swiveltest.Utils.ServerUtils;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MovieDTO> movieList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Loading Please Wait", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Pull Down to Refresh", Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMovies();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadMovies() {
        for (int i = 0; i <= 20; i++) {
            Random r = new Random();
            int imdb = r.nextInt(99 - 10) + 11;
            String url = "http://www.omdbapi.com/?apikey=fc63eef5&i=tt00057" + imdb;
            Callback callback = new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            final String myResponse = response.body().string();
                            Gson gson = new Gson();
                            MovieDTO movieDTO = gson.fromJson(myResponse, MovieDTO.class);
                            movieList.add(movieDTO);
                            adapter = new Adapter(getApplicationContext(), getModeledData(movieList));
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            Log.e("", "" + e);

                        }
                    }
                }
            };

            new ServerUtils(url, callback);
        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    private List<CardModel> getModeledData(List<MovieDTO> movieList) {
        List<CardModel> cardModels = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            MovieDTO movieDTO = movieList.get(i);
            CardModel cardModel = new CardModel();
            cardModel.setTitle(movieDTO.getTitle());
            cardModel.setGenre(movieDTO.getGenre());
            cardModel.setYear(movieDTO.getYear());
            cardModel.setRating(movieDTO.getImdbRating());
            cardModel.setImageUrl(movieDTO.getPoster());
            cardModels.add(cardModel);
        }
        return cardModels;
    }
}
