package com.example.recipeworld;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initially hide the bottom navigation bar for the Get Started, Register, and Login screens
        bottomNavigationView.setVisibility(BottomNavigationView.GONE);

        // Load Get Started screen first
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new GetStartedFragment())
                    .commit();
        }

        // Set up bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.navigation_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.navigation_notifications) {
                selectedFragment = new NotificationsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }

    public void navigateToRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new RegisterFragment())
                .commit();
    }

    public void navigateToLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();
    }

    public void navigateToHome() {
        // Show the navigation bar only when transitioning to the HomeFragment
        bottomNavigationView.setVisibility(BottomNavigationView.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
    }
}
