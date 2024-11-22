package com.example.recipeworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String recipeName);
    }

    public RecipeAdapter(Context context, List<Recipe> recipeList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.recipeList = recipeList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.title.setText(recipe.getTitle());
        holder.subtitle.setText(recipe.getSubtitle());
        holder.ratingBar.setRating(recipe.getRating());

        // Load image with Glide
        Glide.with(context)
                .load(recipe.getImageUrl())
                .centerCrop()
                .into(holder.image);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(recipe.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, subtitle;
        RatingBar ratingBar;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipeImage);
            title = itemView.findViewById(R.id.recipeTitle);
            subtitle = itemView.findViewById(R.id.recipeSubtitle);
            ratingBar = itemView.findViewById(R.id.recipeRating);
        }
    }
}
