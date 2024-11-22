package com.example.recipeworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Set up button click listener
        Button registerButton = view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            // Navigate to the LoginFragment
            ((MainActivity) getActivity()).navigateToLogin();
        });

        return view;
    }
}
