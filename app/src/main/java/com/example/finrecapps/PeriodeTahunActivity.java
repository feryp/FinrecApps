package com.example.finrecapps;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finrecapps.Adapter.PagerBulanAdapter;
import com.example.finrecapps.Adapter.PagerTahunAdapter;
import com.example.finrecapps.Fragment.DadakanBulanFragment;
import com.example.finrecapps.Fragment.KasBulanFragment;
import com.example.finrecapps.Fragment.RekapBulanFragment;
import com.example.finrecapps.Fragment.RutinBulanFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class PeriodeTahunActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerTahun;

    int fragmentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periode_tahun);

        TabLayout tabLayout = findViewById(R.id.tab_layout_tahun);

        spinnerTahun = findViewById(R.id.spinner_tahun);

        ArrayList<String> tahun = new ArrayList<String>();
        int thisTahun = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2010; i <= thisTahun; i++) {
            tahun.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_dropdown, tahun);

        adapter.setDropDownViewResource(R.layout.color_spinner);
        spinnerTahun.setAdapter(adapter);
        spinnerTahun.setOnItemSelectedListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("Rutin"));
        tabLayout.addTab(tabLayout.newTab().setText("Kas"));
        tabLayout.addTab(tabLayout.newTab().setText("Dadakan"));
        tabLayout.addTab(tabLayout.newTab().setText("Rekap"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pagerTahun);
        final PagerAdapter pagerAdapter = new PagerTahunAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                fragmentPosition = tab.getPosition();
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
        bundle.putInt("year", parent.getCount());
        Fragment f;
        switch(this.fragmentPosition){
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
}
