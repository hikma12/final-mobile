package com.example.h071211006_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211006_finalmobile.Model.DataMoviesResponse;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER_MOVIE = "extra_user_movie";
    public static final String EXTRA_USER_TVSHOW = "extra_user_tvshow";
    private TextView tvTitle, tvReleaseDate, tvVoteAverage, tvSynopsis, tvBodyOfSynopsis;
    private ImageView ivBackDrop, ivRatingStar, ivPoster, ivViewMovie, ivViewTVShow;
    private FloatingActionButton fabFav, fabBack;
    private Handler handler;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvReleaseDate = findViewById(R.id.tv_releaseDate);
        tvVoteAverage = findViewById(R.id.tv_rating);
        tvSynopsis = findViewById(R.id.tv_synopsis);
        tvBodyOfSynopsis = findViewById(R.id.tv_bodyOfSynopsis);
        ivBackDrop = findViewById(R.id.iv_backDrop);
        ivRatingStar = findViewById(R.id.iv_ratingStar);
        ivPoster = findViewById(R.id.iv_poster);
        ivViewMovie = findViewById(R.id.iv_viewMovie);
        ivViewTVShow = findViewById(R.id.iv_viewTvShow);
        fabBack = findViewById(R.id.fab_back);
        fabFav = findViewById(R.id.fab_fav);

        handler = new Handler(Looper.getMainLooper());

        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);

        fabBack.setOnClickListener(view -> {
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_USER_MOVIE)) {
                DataMoviesResponse dataMoviesResponse = intent.getParcelableExtra(EXTRA_USER_MOVIE);
                if (dataMoviesResponse != null) {
                    tvTitle.setText(dataMoviesResponse.getTitle());
                    tvReleaseDate.setText(dataMoviesResponse.getReleaseDate());
                    tvBodyOfSynopsis.setText(dataMoviesResponse.getOverview());
                    tvVoteAverage.setText(dataMoviesResponse.getVoteAverage());
                    String poster = "https://image.tmdb.org/t/p/w500" + dataMoviesResponse.getPosterPath();
                    Glide.with(this)
                            .load(poster)
                            .into(ivPoster);

                    String backdrop = "https://image.tmdb.org/t/p/w500" + dataMoviesResponse.getBackdropPath();
                    Glide.with(this)
                            .load(backdrop)
                            .into(ivBackDrop);

                    ivViewTVShow.setVisibility(View.GONE);

                } else {
                    Toast.makeText(this, "Data movie tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (intent != null) {
            if (intent.hasExtra(EXTRA_USER_TVSHOW)) {
                DataTVShowsResponse dataTVShowsResponse = intent.getParcelableExtra(EXTRA_USER_TVSHOW);
                if (dataTVShowsResponse != null) {
                    tvTitle.setText(dataTVShowsResponse.getName());
                    tvReleaseDate.setText(dataTVShowsResponse.getFirstAirDate());
                    tvBodyOfSynopsis.setText(dataTVShowsResponse.getOverview());
                    tvVoteAverage.setText(dataTVShowsResponse.getVoteAverage());
                    String poster = "https://image.tmdb.org/t/p/w500" + dataTVShowsResponse.getPosterPath();
                    Glide.with(this)
                            .load(poster)
                            .into(ivPoster);

                    String backdrop = "https://image.tmdb.org/t/p/w500" + dataTVShowsResponse.getBackdropPath();
                    Glide.with(this)
                            .load(backdrop)
                            .into(ivBackDrop);

                    ivViewMovie.setVisibility(View.GONE);

                } else {
                    Toast.makeText(this, "Data tv show tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        }
        fabFav.setOnClickListener(view -> {
            if (isOnline()) {
                if (intent != null) {
                    if (intent.hasExtra(EXTRA_USER_MOVIE)) {
                        DataMoviesResponse movie = intent.getParcelableExtra(EXTRA_USER_MOVIE);
                        if (movie != null) {
                            if (databaseHelper.isFavorite(movie.getTitle())) {
                                databaseHelper.removeFavorite(movie.getTitle());
                                Toast.makeText(this, "Film dihapus dari favorit", Toast.LENGTH_SHORT).show();
                                fabFav.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite_off_color)));
                            } else {
                                databaseHelper.addFavorite(movie);
                                Toast.makeText(this, "Film ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
                                fabFav.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite_on_color)));
                            }
                        } else {
                            Toast.makeText(this, "Data movie tidak tersedia", Toast.LENGTH_SHORT).show();
                        }
                    } else if (intent.hasExtra(EXTRA_USER_TVSHOW)) {
                        DataTVShowsResponse tvShow = intent.getParcelableExtra(EXTRA_USER_TVSHOW);
                        if (tvShow != null) {
                            if (databaseHelper.isFavorite(tvShow.getName())) {
                                databaseHelper.removeFavorite(tvShow.getName());
                                Toast.makeText(this, "Acara TV dihapus dari favorit", Toast.LENGTH_SHORT).show();
                                fabFav.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite_off_color)));
                            } else {
                                databaseHelper.addFavorite(tvShow);
                                Toast.makeText(this, "Acara TV ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
                                fabFav.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite_on_color)));
                            }
                        } else {
                            Toast.makeText(this, "Data tv show tidak tersedia", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}