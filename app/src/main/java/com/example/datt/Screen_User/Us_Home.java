package com.example.datt.Screen_User;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.datt.R;
import com.google.android.material.navigation.NavigationView;

public class Us_Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_us_home);

        drawerLayout = findViewById(R.id.US_drawerLayout);
        buttonDrawerToggle = findViewById(R.id.Us_buttonDrawer);
        navigationView=findViewById(R.id.Us_Navigation_Menu);
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId=item.getItemId();
                if(itemId == R.id.naMenu){
                    Toast.makeText(Us_Home.this, "MenuClicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.close();
                return false;
            }
        });
    }
}