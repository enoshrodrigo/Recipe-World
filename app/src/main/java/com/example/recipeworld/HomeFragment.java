package com.example.recipeworld;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Flex layout

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Ice cream", "Delicious dessert", 3.5f, "https://thebigmansworld.com/wp-content/uploads/2024/05/strawberry-ice-cream-recipe2.jpg"));
        recipes.add(new Recipe("Chocolate Cake", "Rich chocolate flavor", 4.7f, "https://stylesweet.com/wp-content/uploads/2022/06/DripBestButterCake_Featured-500x500.jpg"));
        recipes.add(new Recipe("Fruit Tart", "Fresh fruits and cream", 2.8f, "https://www.marcellinaincucina.com/wp-content/uploads/2017/09/mini-fruit-tarts-featured.jpg"));
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
}