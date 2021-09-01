package com.example.emergency;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);


        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.view_pager);

        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String appUrl = "https://github.com/mandem296";
                switch (item.getItemId()) {
                    case R.id.option_pizza:
                        item.setChecked(true);
                        Intent pizza = new Intent(Dashboard.this, PizzaActivity.class);
                        startActivity(pizza);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.option_cocktails:
                        item.setChecked(true);
                        Intent cocktail = new Intent(Dashboard.this, CocktailsActivity.class);
                        startActivity(cocktail);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.option_pasta:
                        item.setChecked(true);
                        Intent pasta = new Intent(Dashboard.this, PastaActivity.class);
                        startActivity(pasta);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.contact_us:
                        item.setChecked(true);
                        Intent callUs = new Intent(Intent.ACTION_DIAL);
                        callUs.setData(Uri.parse("tel:0701497597"));
                        drawerLayout.closeDrawers();
                        startActivity(callUs);
                        return true;

                    case R.id.about_us:
                        item.setChecked(true);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(appUrl));
                        drawerLayout.closeDrawers();
                        startActivity(i);
                        return true;

                    case R.id.share_app:
                        item.setChecked(true);
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "This app is available in: " + appUrl);

                        Intent chooser = Intent.createChooser(shareIntent, "Share via");

                        drawerLayout.closeDrawers();

                        if (shareIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(chooser);
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_like:
                Toast.makeText(this, "Liked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_comment:
                Toast.makeText(this, "Comment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
