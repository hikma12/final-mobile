package com.example.h071211006_finalmobile.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.h071211006_finalmobile.Adapter.FavoriteAdapter;
import com.example.h071211006_finalmobile.DatabaseHelper;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.example.h071211006_finalmobile.R;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    private RecyclerView rvFavorites;
    private FavoriteAdapter favoriteAdapter;
    private TextView tvNoFav, tvNoInternet;
    private ImageView ivRetry;
    private List<Object> favoritesList = new ArrayList<Object>();
    private List<DataTVShowsResponse> favoritesList1 = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private ProgressBar pbFavorites;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbFavorites = view.findViewById(R.id.pb_favorites);
        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvNoFav = view.findViewById(R.id.tv_noFav);
        tvNoInternet = view.findViewById(R.id.tv_noInternet);
        ivRetry = view.findViewById(R.id.iv_retry);

        databaseHelper = new DatabaseHelper(getContext());
        favoriteAdapter = new FavoriteAdapter(requireContext(), favoritesList);
        rvFavorites.setAdapter(favoriteAdapter);

        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavorites.setAdapter(favoriteAdapter);

        getDataFavorites();
    }

    private void getDataFavorites() {
        pbFavorites.setVisibility(View.VISIBLE);
        rvFavorites.setVisibility(View.GONE);
        tvNoInternet.setVisibility(View.GONE);
        ivRetry.setVisibility(View.GONE);

        favoritesList.addAll(databaseHelper.getAllFavorites());
        favoriteAdapter.notifyDataSetChanged();

        updateUI();
    }

    private void updateUI() {
        pbFavorites.setVisibility(View.GONE);
        rvFavorites.setVisibility(View.VISIBLE);

        if (favoritesList.isEmpty()) {
            tvNoFav.setVisibility(View.VISIBLE);
        } else {
            tvNoFav.setVisibility(View.GONE);
            favoriteAdapter.notifyDataSetChanged();
        }
    }
}
