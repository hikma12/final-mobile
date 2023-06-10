package com.example.h071211006_finalmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.h071211006_finalmobile.DetailActivity;
import com.example.h071211006_finalmobile.Model.DataMoviesResponse;
import com.example.h071211006_finalmobile.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {
    Context context;
    private List<DataMoviesResponse> dataMoviesResponseList;

    public MovieAdapter(Context context, List<DataMoviesResponse> dataMoviesResponseList) {
        this.context = context;
        this.dataMoviesResponseList = dataMoviesResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataMoviesResponse dataMoviesResponse = dataMoviesResponseList.get(position);
        holder.tvTitle.setText(dataMoviesResponse.getTitle());
        holder.tvRelease.setText(dataMoviesResponse.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_USER, dataMoviesResponse.getId());
            holder.itemView.getContext().startActivity(intent);
        });

        String poster = "https://image.tmdb.org/t/p/w500" + dataMoviesResponse.getPosterPath();
        Glide.with(holder.ivPoster.getContext())
                .load(poster)
                .into(holder.ivPoster);

//        String backdrop = "https://image.tmdb.org/t/p/w500" + dataMoviesResponse.getBackdropPath();
//        Glide.with(holder.ivBackdrop.getContext())
//                .load(backdrop)
//                .into(holder.ivBackdrop);
    }

    @Override
    public int getItemCount() {
        return dataMoviesResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView ivPoster, ivBackdrop;
        private TextView tvTitle, tvRelease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_movie);
            ivPoster = itemView.findViewById(R.id.iv_movie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRelease = itemView.findViewById(R.id.tv_release);
            ivBackdrop = itemView.findViewById(R.id.iv_backDrop);
        }
    }
}
