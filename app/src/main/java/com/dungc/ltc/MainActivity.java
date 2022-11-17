package com.dungc.ltc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.dungc.ltc.databinding.ActivityMainBinding;
import com.dungc.ltc.fcm.MyFirebaseMessagingService;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpNavigation();
    }

    private void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(
                R.id.main_container
        );
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
//            appBarConfiguration =
//                    new AppBarConfiguration.Builder(R.id.home, R.id.mall, R.id.info, R.id.profile).build();
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String title = bundle.getString(MyFirebaseMessagingService.NOTIFICATION_KEY);
            Navigation.findNavController(this, R.id.main_container).navigate(R.id.action_home_to_notify);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.main_container);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}