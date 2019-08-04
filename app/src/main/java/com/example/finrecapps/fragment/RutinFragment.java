package com.example.finrecapps.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finrecapps.R;
import com.mancj.slideup.SlideUp;


public class RutinFragment extends Fragment {

    private SlideUp slideUp;
    private View scrim;
    private View slideView;
    private FloatingActionButton fab_rutin;

    Spinner spinnerBulan;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rutin, container, false);

        spinnerBulan = v.findViewById(R.id.spinner_bulan);

        slideView = v.findViewById(R.id.slideView);
        scrim = v.findViewById(R.id.scrim);
        fab_rutin = v.findViewById(R.id.fab_rutin);

        slideUp = new SlideUp(slideView);
        slideUp.hideImmediately();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.bulan_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerBulan.setAdapter(adapter);

        fab_rutin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.animateIn();
                fab_rutin.hide();
            }
        });

        slideUp.setSlideListener(new SlideUp.SlideListener() {
            @Override
            public void onSlideDown(float v) {
                scrim.setAlpha(1 - (v / 100));
            }

            @Override
            public void onVisibilityChanged(int i) {
                if (i == View.GONE){
                    fab_rutin.show();
                }


            }
        });

        return v;
    }

}
