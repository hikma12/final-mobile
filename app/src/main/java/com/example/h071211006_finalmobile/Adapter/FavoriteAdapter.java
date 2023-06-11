package com.example.h071211006_finalmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211006_finalmobile.DetailActivity;
import com.example.h071211006_finalmobile.Model.DataMoviesResponse;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.example.h071211006_finalmobile.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Object> favoritesList;
    private Context context;

    public FavoriteAdapter(Context context, List<Object> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Object favoriteItem = favoritesList.get(position);

        if (favoriteItem instanceof DataMoviesResponse) {
            DataMoviesResponse dataMoviesResponse = (DataMoviesResponse) favoriteItem;
            holder.tvTitle.setText(dataMoviesResponse.getTitle());
            holder.tvReleaseDate.setText(dataMoviesResponse.getReleaseDate());
            String poster = "https://image.tmdb.org/t/p/w500" + dataMoviesResponse.getPosterPath();
            Glide.with(context)
                    .load(poster)
                    .into(holder.ivPoster);

        } else if (favoriteItem instanceof DataTVShowsResponse) {
            DataTVShowsResponse dataTVShowsResponse = (DataTVShowsResponse) favoriteItem;
            holder.tvTitle.setText(dataTVShowsResponse.getName());
            holder.tvReleaseDate.setText(dataTVShowsResponse.getFirstAirDate());
            String poster = "https://image.tmdb.org/t/p/w500" + dataTVShowsResponse.getPosterPath();
            Glide.with(context)
                    .load(poster)
                    .into(holder.ivPoster);
        }

        holder.cardViewFavorites.setOnClickListener(v -> {
            Object favoriteItem1 = favoritesList.get(holder.getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            if (favoriteItem1 instanceof DataMoviesResponse) {
                intent.putExtra(DetailActivity.EXTRA_USER_MOVIE, (Parcelable) favoriteItem1);
            } else if (favoriteItem1 instanceof DataTVShowsResponse) {
                intent.putExtra(DetailActivity.EXTRA_USER_TVSHOW, (Parcelable) favoriteItem1);
            }
            context.startActivity(intent);
        });
    }

        @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewFavorites;
        private TextView tvTitle, tvReleaseDate;
        private ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewFavorites = itemView.findViewById(R.id.cardView_favorites);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_year);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }
    }
}
