package com.example.finrecapps;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finrecapps.Adapter.PagerBulanAdapter;
import com.example.finrecapps.Database.RutinDbHelper;
import com.example.finrecapps.Fragment.DadakanBulanFragment;
import com.example.finrecapps.Fragment.KasBulanFragment;
import com.example.finrecapps.Fragment.RekapBulanFragment;
import com.example.finrecapps.Fragment.RutinBulanFragment;
import com.example.finrecapps.Model.Rutin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriodeBulanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    Spinner spinnerBulan;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periode_bulan);

        TabLayout tabLayout = findViewById(R.id.tab_layout_bulan);

        spinnerBulan = findViewById(R.id.spinner_bulan);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.bulan_arrays,
                R.layout.simple_spinner_dropdown);

        adapter.setDropDownViewResource(R.layout.color_spinner);
        spinnerBulan.setAdapter(adapter);
        spinnerBulan.setOnItemSelectedListener(this);

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
                position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putInt("month", position);
        Fragment f;
        switch(this.position){
            case 0:
                f = new RutinBulanFragment();
                f.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().detach(f).attach(f).commit();
                break;
            case 1:
                f = new KasBulanFragment();
                f.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().detach(f).attach(f).commit();
                break;
            case 2:
                f = new DadakanBulanFragment();
                f.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().detach(f).attach(f).commit();
                break;
            case 3:
                f = new RekapBulanFragment();
                f.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().detach(f).attach(f).commit();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

}
