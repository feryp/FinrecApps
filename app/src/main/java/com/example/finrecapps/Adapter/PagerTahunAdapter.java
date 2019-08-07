package com.example.finrecapps.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.finrecapps.Fragment.DadakanTahunFragment;
import com.example.finrecapps.Fragment.KasTahunFragment;
import com.example.finrecapps.Fragment.RekapTahunFragment;
import com.example.finrecapps.Fragment.RutinTahunFragment;

public class PagerTahunAdapter extends FragmentStatePagerAdapter {

    int counttab;

    public PagerTahunAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                RutinTahunFragment rutinTahunFragment = new RutinTahunFragment();
                return rutinTahunFragment;
            case 1:
                KasTahunFragment kasTahunFragment = new KasTahunFragment();
                return kasTahunFragment;
            case 2:
                DadakanTahunFragment dadakanTahunFragment = new DadakanTahunFragment();
                return dadakanTahunFragment;
            case 3:
                RekapTahunFragment rekapTahunFragment = new RekapTahunFragment();
                return rekapTahunFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
