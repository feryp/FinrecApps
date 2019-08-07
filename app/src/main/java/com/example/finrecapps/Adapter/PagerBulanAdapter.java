package com.example.finrecapps.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.finrecapps.Fragment.DadakanBulanFragment;
import com.example.finrecapps.Fragment.KasBulanFragment;
import com.example.finrecapps.Fragment.RekapBulanFragment;
import com.example.finrecapps.Fragment.RutinBulanFragment;

public class PagerBulanAdapter extends FragmentStatePagerAdapter {

    int counttab;

    public PagerBulanAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                RutinBulanFragment rutinBulanFragment = new RutinBulanFragment();
                
                return rutinBulanFragment;
            case 1:
                KasBulanFragment kasBulanFragment = new KasBulanFragment();
                return kasBulanFragment;
            case 2:
                DadakanBulanFragment dadakanBulanFragment = new DadakanBulanFragment();
                return dadakanBulanFragment;
            case 3:
                RekapBulanFragment rekapBulanFragment = new RekapBulanFragment();
                return rekapBulanFragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return counttab;
    }
}
