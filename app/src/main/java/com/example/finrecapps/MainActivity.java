package com.example.finrecapps;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    LinearLayout periode_bulan, periode_tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        periode_bulan = findViewById(R.id.container_bulan);
        periode_tahun = findViewById(R.id.container_tahun);

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


    }
}
