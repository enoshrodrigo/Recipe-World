package com.example.recipeworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailFragment extends Fragment {

    private static final String ARG_RECIPE_NAME = "recipe_name";

    public static RecipeDetailFragment newInstance(String recipeName) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RECIPE_NAME, recipeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Flex layout

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Ice cream", "Delicious dessert", 3.5f, "https://static.vecteezy.com/system/resources/thumbnails/022/962/943/small_2x/strawberry-cone-ice-cream-3d-sweets-icon-png.png"));
        recipes.add(new Recipe("Chocolate Cake", "Rich chocolate flavor", 4.7f, "https://images.creativefabrica.com/products/thumbnails/2023/09/13/AMvczsNrT/2VLdj7wPpK8HhW0qbbvmsqo6oFr.png"));
        recipes.add(new Recipe("Fruit Tart", "Fresh fruits and cream", 2.8f, "https://cdn3d.iconscout.com/3d/premium/thumb/fruit-tart-3d-icon-download-in-png-blend-fbx-gltf-file-formats--delicious-logo-mini-tartlet-western-food-dessert-pack-drink-icons-8807384.png?f=webp"));
        recipes.add(new Recipe("Pancakes", "Fluffy breakfast treat", 4.2f, "https://cdn.loveandlemons.com/wp-content/uploads/2022/09/oatmeal-pancakes.jpg"));
        recipes.add(new Recipe("Lasagna", "Layered pasta delight", 4.8f, "https://www.jessicagavin.com/wp-content/uploads/2017/07/meat-lasagna-1200.jpg"));
        recipes.add(new Recipe("Smoothie Bowl", "Healthy and vibrant", 4.5f, "https://images.themodernproper.com/billowy-turkey/production/posts/2021/Smoothie-Bowl-8.jpeg?w=960&h=960&q=82&fm=jpg&fit=crop&dm=1641225383&s=139a98620a1c262e385ffe030a55cbc2"));
        recipes.add(new Recipe("Cupcakes", "Perfect for parties", 3.9f, "https://easydessertrecipes.com/wp-content/uploads/2021/03/chocolate-cupcake-recipe-6-500x500.jpg"));


        RecipeAdapter adapter = new RecipeAdapter(getContext(), recipes,recipeName -> {
            // Handle item click: Navigate to another fragment
            RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipeName);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null) // Add to back stack so user can navigate back
                    .commit();
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recipe Name TextView
        TextView recipeNameTextView = view.findViewById(R.id.recipeNameTextView);
        if (getArguments() != null) {
            String recipeName = getArguments().getString(ARG_RECIPE_NAME);
            recipeNameTextView.setText(recipeName); // Display the recipe name
        }

        // Recipe Image Animation (Fade-In)
        ImageView recipeImage = view.findViewById(R.id.recipeImage);
        recipeImage.setAlpha(0f);
        recipeImage.animate().alpha(1f).setDuration(1000).start();

        // Recipe Text Animation (Fade-In)
        recipeNameTextView.setAlpha(0f);
        recipeNameTextView.animate().alpha(1f).setDuration(1000).start();

        // Button Animation (Bounce)
        Button startBakingButton = view.findViewById(R.id.startBakingButton);
        startBakingButton.setScaleX(0f);
        startBakingButton.setScaleY(0f);
        startBakingButton.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .setInterpolator(new BounceInterpolator())
                .start();

        // Horizontal ScrollView Animation
        HorizontalScrollView ingredientScrollView = view.findViewById(R.id.ingredientScrollView);
        if (ingredientScrollView != null) {
            ingredientScrollView.setTranslationX(-500f);
            ingredientScrollView.animate().translationX(0f).setDuration(1000).start();
        }

        // Slide-In Animation for Directions Section
        LinearLayout directionsSection = view.findViewById(R.id.directionsSection);
        if (directionsSection != null) {
            directionsSection.setTranslationY(500f);
            directionsSection.animate().translationY(0f).setDuration(800).start();
        }
    }
}
