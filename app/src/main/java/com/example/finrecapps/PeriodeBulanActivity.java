package com.example.finrecapps;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.finrecapps.Adapter.PagerBulanAdapter;

public class PeriodeBulanActivity extends AppCompatActivity {

    Spinner spinnerBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periode_bulan);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        spinnerBulan = findViewById(R.id.spinner_bulan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bulan_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerBulan.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Rutin"));
        tabLayout.addTab(tabLayout.newTab().setText("Kas"));
        tabLayout.addTab(tabLayout.newTab().setText("Dadakan"));
        tabLayout.addTab(tabLayout.newTab().setText("Rekap"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pagerBulan);
        final PagerAdapter pagerAdapter = new PagerBulanAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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
    }
}
