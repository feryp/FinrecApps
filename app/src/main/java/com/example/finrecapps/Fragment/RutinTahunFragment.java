package com.example.finrecapps.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finrecapps.Adapter.RutinAdapter;
import com.example.finrecapps.Database.RutinDbHelper;
import com.example.finrecapps.Model.Rutin;
import com.example.finrecapps.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RutinTahunFragment extends Fragment {


    RecyclerView rvRutin;
    TextView tvJmlPertahun;
    RecyclerView.LayoutManager layoutManager;
    List<Rutin> listRutin;
    RutinAdapter adapter;
    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    Spinner spinnerYear;
    double totalPertahun;

    int yearPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            yearPosition = getArguments().getInt("year");
        } catch (NullPointerException e) {


        }


        View v = inflater.inflate(R.layout.fragment_rutin_tahun, container, false);
        //INIT VIEW
        rvRutin = v.findViewById(R.id.rv_rutin);
        rvRutin.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rvRutin.setLayoutManager(layoutManager);
        tvJmlPertahun = v.findViewById(R.id.tv_jumlah_pertahun_rutin);
        spinnerYear = getActivity().findViewById(R.id.spinner_tahun);
        //INIT VIEW END

        spinnerYear.setSelection(spinnerYear.getCount()-1);

        //DATA
        try {

            RutinDbHelper helper = new RutinDbHelper(getContext());
            listRutin = helper.selectAll();
            List<Rutin> filterList = new ArrayList<>();
            for (Rutin rutin : listRutin) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rutin.getTimeInMilis());
                Log.v("getlongfdb", String.valueOf(rutin.getTimeInMilis()));
                int m = cal.get(Calendar.MONTH);
                int y = cal.get(Calendar.YEAR);
                if (y == Integer.parseInt(spinnerYear.getSelectedItem().toString())) {
                    filterList.add(new Rutin(rutin.getId(), rutin.getTimeInMilis(), rutin.getJenisAkun(), rutin.getSaldo()));
                    totalPertahun = totalPertahun + rutin.getSaldo();
                }
            }


            adapter = new RutinAdapter(getContext(), listRutin, null);
//            rvRutin.notifyAll();
            rvRutin.setAdapter(adapter);
            String total = decimalFormat.format(totalPertahun);
            tvJmlPertahun.setText("Rp " + total);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        //DATA END

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                totalPertahun = 0;
                try {

                    RutinDbHelper helper = new RutinDbHelper(getContext());
                    listRutin = helper.selectAll();
                    List<Rutin> filterList = new ArrayList<>();
                    for (Rutin rutin : listRutin) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(rutin.getTimeInMilis());
                        Log.v("getlongfdb", String.valueOf(rutin.getTimeInMilis()));
                        int m = cal.get(Calendar.MONTH);
                        int y = cal.get(Calendar.YEAR);
                        Log.v("year", y + " " + parent.getSelectedItem().toString());
                        if (y == Integer.parseInt(spinnerYear.getSelectedItem().toString())) {
                            filterList.add(new Rutin(rutin.getId(), rutin.getTimeInMilis(), rutin.getJenisAkun(), rutin.getSaldo()));
                            totalPertahun = totalPertahun + rutin.getSaldo();
                        }
                    }


                    adapter = new RutinAdapter(getContext(), filterList, null);
                    rvRutin.setAdapter(adapter);
                    String total = decimalFormat.format(totalPertahun);
                    tvJmlPertahun.setText("Rp " + total);

                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
}
