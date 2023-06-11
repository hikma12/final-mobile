package com.example.h071211006_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.h071211006_finalmobile.Fragment.FavoritesFragment;
import com.example.h071211006_finalmobile.Fragment.MoviesFragment;
import com.example.h071211006_finalmobile.Fragment.TVShowsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnMovies, btnTVShows, btnFavorites;
    private MoviesFragment moviesFragment = new MoviesFragment();
    private TVShowsFragment tvShowsFragment = new TVShowsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMovies = findViewById(R.id.iv_btnMovies);
        btnFavorites = findViewById(R.id.iv_btnFavorites);
        btnTVShows = findViewById(R.id.iv_btnTVShows);

        btnMovies.setColorFilter(Color.WHITE);
        btnFavorites.setColorFilter(Color.WHITE);
        btnTVShows.setColorFilter(Color.WHITE);

        btnMovies.setOnClickListener(this);
        btnFavorites.setOnClickListener(this);
        btnTVShows.setOnClickListener(this);

//        Intent intent = getIntent();
//        String fragment = intent.getStringExtra("fragment");
//        if (fragment != null) {
//            switch (fragment) {
//                case "movies":
//                    switchToMoviesFragment();
//                    break;
//                case "tvshows":
//                    switchToTvShowsFragment();
//                    break;
//                case "favorites":
//                    switchToFavoritesFragment();
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            switchToMoviesFragment();
//        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(MoviesFragment.class.getSimpleName());
        if (!(fragment instanceof MoviesFragment)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameContainer, moviesFragment,
                            MoviesFragment.class.getSimpleName())
                    .commit();
//        }
        getSupportActionBar().setTitle("Movies");
    }

//    private void switchToMoviesFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        Fragment moviesFragment = new MoviesFragment();
//        fragmentTransaction.replace(R.id.frameContainer, moviesFragment);
//
//        fragmentTransaction.commit();
//    }
//
//    private void switchToTvShowsFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        Fragment tvShowsFragment = new TVShowsFragment();
//        fragmentTransaction.replace(R.id.frameContainer, tvShowsFragment);
//
//        fragmentTransaction.commit();
//    }
//
//    private void switchToFavoritesFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        Fragment favoritesFragment = new FavoritesFragment();
//        fragmentTransaction.replace(R.id.frameContainer, favoritesFragment);
//
//        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_btnMovies:
                Bundle bundle = getIntent().getExtras();
                moviesFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, moviesFragment).commit();
                getSupportActionBar().setTitle("Movies");
                break;
            case R.id.iv_btnTVShows:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, tvShowsFragment).commit();
                getSupportActionBar().setTitle("TV Shows");
                break;
            case R.id.iv_btnFavorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, favoritesFragment).commit();
                getSupportActionBar().setTitle("Favorites");
                break;

        }
    }
}