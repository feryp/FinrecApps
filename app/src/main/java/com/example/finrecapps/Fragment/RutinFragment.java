package com.example.finrecapps.Fragment;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.finrecapps.Adapter.RutinAdapter;
import com.example.finrecapps.Database.RutinDbHelper;
import com.example.finrecapps.MainActivity;
import com.example.finrecapps.Model.Rutin;
import com.example.finrecapps.R;
import com.mancj.slideup.SlideUp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


public class RutinFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private SlideUp slideUp;
    private View scrim;
    private View slideView;
    private FloatingActionButton fab_rutin;

    Spinner spinnerBulan;

    Button btnSimpan, btnHapus;

    TextInputEditText etTotalTabungan, etSaldo, etTanggalTabungan;

    RecyclerView rvRutin;

    long tgl;

    List<Rutin> listRutin;
    RutinAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rutin, container, false);

        rvRutin = v.findViewById(R.id.rv_rutin);
        rvRutin.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRutin.setHasFixedSize(true);
        try{

            RutinDbHelper helper = new RutinDbHelper(getContext());
            listRutin = helper.selectAll();
            List<Rutin> asd = new ArrayList<>();
            for(Rutin rutin :listRutin){
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(Long.valueOf(rutin.getTimeInMilis()));
                Log.v("getlongfdb", String.valueOf(rutin.getTimeInMilis()));
                int m = cal.get(Calendar.MONTH);
                if(m == 0){
                    asd.add(new Rutin(rutin.getId(), Long.valueOf(rutin.getTimeInMilis()),rutin.getTotalTabungan(),rutin.getSaldo()));
                }
            }
            adapter = new RutinAdapter(getContext(), asd);
            rvRutin.setAdapter(adapter);
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }


        spinnerBulan = v.findViewById(R.id.spinner_bulan);



        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinnerBulan.getSelectedItem();
//                Log.v("position", )
                rvRutin.removeAllViews();

//                String as = null;
//                switch (position){
//                    case 0:
//                        as = "01";
//                        break;
//                    case 1:
//                        as = "02";
//                        break;
//                    case 2:
//                        as = "03";
//                        break;
//                    case 3:
//                        as = "04";
//                        break;
//                    case 4:
//                        as = "05";
//                        break;
//                    case 5:
//                        as = "06";
//                        break;
//                    case 6:
//                        as = "07";
//                        break;
//                    case 7:
//                        as = "08";
//                        break;
//                    case 8:
//                        as = "09";
//                        break;
//                    case 9:
//                        as = "10";
//                        break;
//                    case 10:
//                        as = "11";
//                        break;
//                    case 11:
//                        as = "12";
//                        break;
//                }

                List<Rutin> asd = new ArrayList<>();
                for(Rutin rutin :listRutin){
                    Calendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(Long.valueOf(rutin.getTimeInMilis()));

                    int y = cal.get(Calendar.YEAR);
                    int m = cal.get(Calendar.MONTH);
                    int d = cal.get(Calendar.DAY_OF_MONTH);
                    Log.v("testselect", y + " " + m + " "+d);
                    Log.v("position", m + " p "+ position);
                    if(m == position){
                        asd.add(new Rutin(rutin.getId(), Long.valueOf(rutin.getTimeInMilis()),rutin.getTotalTabungan(),rutin.getSaldo()));
                    }
                }
                adapter = new RutinAdapter(getContext(), asd);
                adapter.notifyDataSetChanged();
                rvRutin.setAdapter(adapter);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        btnSimpan = v.findViewById(R.id.btnSimpan);
        btnHapus = v.findViewById(R.id.btnHapus);

        etTotalTabungan = v.findViewById(R.id.et_total_tabungan);
        etSaldo = v.findViewById(R.id.et_saldo);


        etTanggalTabungan = v.findViewById(R.id.et_tanggal_tabungan);
        etTanggalTabungan.setKeyListener(null);


        btnSimpan.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
        etTanggalTabungan.setOnClickListener(this);



        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSimpan:

                long tanggalTabungan = tgl;
                double totalTabungan = Double.parseDouble(etTotalTabungan.getText().toString());
                double saldo = Double.parseDouble(etSaldo.getText().toString());

                RutinDbHelper helper = new RutinDbHelper(getContext());
                helper.insert(tanggalTabungan, totalTabungan, saldo);
                break;
            case R.id.btnHapus:
                break;

            case R.id.et_tanggal_tabungan:
                Calendar cal = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getContext(),  this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

                dialog.show();

                break;

        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        DateFormat format = new SimpleDateFormat("dd MMM yyyy");

        etTanggalTabungan.setText(format.format(c.getTime()));

        tgl = c.getTimeInMillis();

        Calendar s = Calendar.getInstance();
        s.setTimeInMillis(tgl);

        Log.v("testinsert", String.valueOf(s.getTimeInMillis()));
        Log.v("testinsert", String.valueOf(s.get(Calendar.MONTH)));


    }
}
