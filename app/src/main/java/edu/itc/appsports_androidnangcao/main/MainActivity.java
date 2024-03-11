package edu.itc.appsports_androidnangcao.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.itc.appsports_androidnangcao.Fragment.Fragment_Canhan;
import edu.itc.appsports_androidnangcao.Fragment.Fragment_Home;
import edu.itc.appsports_androidnangcao.Fragment.Fragment_giohang;
import edu.itc.appsports_androidnangcao.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    Fragment currentfragment;
    Fragment_Home fragment_home = new Fragment_Home();
    Fragment_giohang fragment_giohang = new Fragment_giohang();
    Fragment_Canhan fragment_canhan = new Fragment_Canhan();
    BadgeDrawable badgeHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        bottomNavigationView.setBackground(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        clearFragmentHome();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.mnuHome:
                        currentfragment = fragment_home;
                        badgeHome.setVisible(false);
                        break;
                    case R.id.mnuCart:
                        currentfragment = fragment_giohang;
                        break;
                    case R.id.mnuAccount:
                        currentfragment = fragment_canhan;
                        break;
                }
                Loadfragment(currentfragment);
                return true;
            }
        });
        Loadfragment(fragment_home);
        LoadbadgeHome();
    }

    private void clearFragmentHome() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void LoadbadgeHome() {
        badgeHome = bottomNavigationView.getOrCreateBadge(R.id.mnuHome);
        badgeHome.setNumber(2);
        badgeHome.setBackgroundColor(Color.RED);
    }

    private void anhxa() {
        bottomNavigationView = findViewById(R.id.bot_nav_view);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
    }
    void Loadfragment(Fragment f){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framemain,f);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}