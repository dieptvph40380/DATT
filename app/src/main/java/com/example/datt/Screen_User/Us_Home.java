package com.example.datt.Screen_User;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.datt.R;
import com.google.android.material.navigation.NavigationView;

public class Us_Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton buttonDrawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_us_home);
        // Ánh xạ các thành phần giao diện
        // Setup DrawerLayout and Toolbar
        drawerLayout = findViewById(R.id.US_drawerLayout);
        buttonDrawerToggle = findViewById(R.id.menu_button); // Hamburger icon
        toolbar = findViewById(R.id.toolbar);
        title =findViewById(R.id.title);



        // Setup NavigationView
        navigationView = findViewById(R.id.Us_Navigation_Menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.naMenu) {
                    Toast.makeText(Us_Home.this, "Menu clicked", Toast.LENGTH_SHORT).show();
                    replaceFragment(new Us_Home_fragment());
                    title.setText("Home");
                } else if (itemId == R.id.naBook) {
                    Toast.makeText(Us_Home.this, "Book clicked", Toast.LENGTH_SHORT).show();
                    replaceFragment(new Us_Book());
                    title.setText("Book");
                }
                drawerLayout.close();
                return false;
            }

        });

        // Hamburger button click to open Drawer
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        // Load the default Fragment
        replaceFragment(new Us_Home_fragment());
    }

    // Hàm thay thế Fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
