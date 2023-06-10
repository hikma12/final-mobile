package com.example.h071211006_finalmobile.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h071211006_finalmobile.API.ApiConfig;
import com.example.h071211006_finalmobile.Adapter.TVShowsAdapter;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.example.h071211006_finalmobile.Model.TVShows;
import com.example.h071211006_finalmobile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsFragment extends Fragment {
    private ProgressBar pbTVShows;
    private RecyclerView rvTvShows;
    private ImageView ivRetry;
    private TextView tvNoInternet;
    private Handler handler;
    LinearLayoutManager layoutManager;
    List<DataTVShowsResponse> dataTVShowsResponseList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvShows = view.findViewById(R.id.rv_tvShows);
        ivRetry = view.findViewById(R.id.iv_retry);
        pbTVShows = view.findViewById(R.id.pb_tvShows);
        tvNoInternet = view.findViewById(R.id.tv_noInternet);

        handler = new Handler(Looper.getMainLooper());

        layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        rvTvShows.setLayoutManager(layoutManager);

        getDataUSer();

    }

    private void getDataUSer() {
        if (isNetworkAvailable()) {
            pbTVShows.setVisibility(View.VISIBLE);
            rvTvShows.setVisibility(View.GONE);
            tvNoInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);

            Call<TVShows> client = ApiConfig.getApiService().getTVShows("a48f2fbf2e8718cac04cd4530fdc05e6");
            client.enqueue(new Callback<TVShows>() {
                @Override
                public void onResponse(Call<TVShows> call, Response<TVShows> response) {
                    pbTVShows.setVisibility(View.GONE);
                    rvTvShows.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            dataTVShowsResponseList.addAll(response.body().getData());
                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(getActivity(), "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<TVShows> call, Throwable t) {
                    Toast.makeText(getActivity(), "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });

            TVShowsAdapter tvShowsAdapter = new TVShowsAdapter(getActivity(), dataTVShowsResponseList);
            rvTvShows.setLayoutManager(layoutManager);
            rvTvShows.setAdapter(tvShowsAdapter);

        } else {
            showRetryButton();
        }
    }
    private void showRetryButton() {
        tvNoInternet.setVisibility(View.VISIBLE);
        ivRetry.setVisibility(View.VISIBLE);
        pbTVShows.setVisibility(View.GONE);

        ivRetry.setOnClickListener(view -> {
            tvNoInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);
            pbTVShows.setVisibility(View.VISIBLE);

            handler.postDelayed(() -> {
                pbTVShows.setVisibility(View.GONE);
                getDataUSer();
            }, 500);
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}