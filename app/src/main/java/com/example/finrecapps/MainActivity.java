package com.example.finrecapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    LinearLayout periode_bulan, periode_tahun;

    TextView tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        periode_bulan = findViewById(R.id.container_bulan);
        periode_tahun = findViewById(R.id.container_tahun);
        tvLogout = findViewById(R.id.tv_logout);



        periode_bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bulan = new Intent(MainActivity.this, PeriodeBulanActivity.class);
                startActivity(bulan);
            }
        });

        periode_tahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tahun = new Intent(MainActivity.this, PeriodeTahunActivity.class);
                startActivity(tahun);
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(getString(R.string.shared_pref_password), Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putBoolean("isLogin", false);

                edit.apply();

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


}
