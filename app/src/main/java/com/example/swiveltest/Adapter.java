package com.example.swiveltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiveltest.CardModel.CardModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<CardModel> data;


    Adapter(Context context, List<CardModel> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.movie_view, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CardModel cardModel = data.get(i);

        viewHolder.textTitle.setText(cardModel.getTitle());
        viewHolder.textGenre.setText(cardModel.getGenre());
        viewHolder.textYear.setText(cardModel.getYear());
        viewHolder.textRating.setText(cardModel.getRating());
        viewHolder.textImgUrl.setText(cardModel.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textGenre, textYear, textRating, textImgUrl;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.movieName);
            textGenre = itemView.findViewById(R.id.Genre);
            textYear = itemView.findViewById(R.id.Year);
            textRating = itemView.findViewById(R.id.ImbdRating);
            textImgUrl = itemView.findViewById(R.id.poster);

        }
    }

}
