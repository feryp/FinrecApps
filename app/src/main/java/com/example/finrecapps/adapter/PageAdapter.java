package com.example.finrecapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.finrecapps.fragment.DadakanFragment;
import com.example.finrecapps.fragment.KasFragment;
import com.example.finrecapps.fragment.RekapFragment;
import com.example.finrecapps.fragment.RutinFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;

    public PageAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                RutinFragment rutinFragment = new RutinFragment();
                return rutinFragment;
            case 1:
                KasFragment kasFragment = new KasFragment();
                return kasFragment;
            case 2:
                DadakanFragment dadakanFragment = new DadakanFragment();
                return dadakanFragment;
            case 3:
                RekapFragment rekapFragment = new RekapFragment();
                return rekapFragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return counttab;
    }
}
