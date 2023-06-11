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
import com.example.h071211006_finalmobile.DetailActivity;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.example.h071211006_finalmobile.R;

import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.ViewHolder> {
    Context context;
    private List<DataTVShowsResponse> dataTVShowsResponseList;

    public TVShowsAdapter(Context context, List<DataTVShowsResponse> dataTVShowsResponseList) {
        this.context = context;
        this.dataTVShowsResponseList = dataTVShowsResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new TVShowsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataTVShowsResponse dataTVShowsResponse = dataTVShowsResponseList.get(position);
        holder.tvTitle.setText(dataTVShowsResponse.getName());
        holder.tvRelease.setText(dataTVShowsResponse.getFirstAirDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_USER_TVSHOW, dataTVShowsResponse);
                context.startActivity(intent);
            }
        });

        String poster = "https://image.tmdb.org/t/p/w500" + dataTVShowsResponse.getPosterPath();
        Glide.with(holder.ivPoster.getContext())
                .load(poster)
                .into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return dataTVShowsResponseList.size();
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
