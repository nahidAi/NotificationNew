package test.notification.com.notification.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import test.notification.com.notification.Adapter.AdapterFragment;
import test.notification.com.notification.DatabaseHelper;
import test.notification.com.notification.R;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuToolbar;
    DatabaseHelper databaseHelper;
    private FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);


        databaseHelper = new DatabaseHelper(MainActivity.this);
        context = getApplicationContext();
        setTabOption();
        setNavigationViewAndFloating();


    }
    private void setTabOption() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager()));

        TabLayout tabStrip = (TabLayout) findViewById(R.id.tabLayout);
        tabStrip.setupWithViewPager(viewPager);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();

        }

    }
    public void setNavigationViewAndFloating() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(floatingActionButton,"کلیک شد",Snackbar.LENGTH_LONG).show();
            }
        });


        menuToolbar = (ImageView) findViewById(R.id.menu_toolbar);
        menuToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.setting) {
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

    }
}
