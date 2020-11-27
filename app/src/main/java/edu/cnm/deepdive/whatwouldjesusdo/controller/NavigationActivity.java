package edu.cnm.deepdive.whatwouldjesusdo.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfiguration.Builder;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity {

  private ActivityNavigationBinding binding;
  private AppBarConfiguration appBarConfig;
  private NavController navController;

  private void setupNavigation() {
    appBarConfig = new Builder(R.id.navigation_bible, R.id.navigation_replay, R.id.navigation_saved,
        R.id.navigation_profile, R.id.navigation_settings)
        .setOpenableLayout(binding.drawerLayout)
        .build();
    //noinspection ConstantConditions
    navController = ((NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment)).getNavController();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    NavigationUI.setupWithNavController(binding.navView, navController);
  }
}
