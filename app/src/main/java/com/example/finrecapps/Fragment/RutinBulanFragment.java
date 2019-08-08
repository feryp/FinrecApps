package com.example.finrecapps.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finrecapps.Adapter.RutinAdapter;
import com.example.finrecapps.Database.RutinDbHelper;
import com.example.finrecapps.Model.Rutin;
import com.example.finrecapps.R;
import com.mancj.slideup.SlideUp;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RutinBulanFragment extends Fragment
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private SlideUp slideUp;
    private View scrim;
    private View slideView;
    private FloatingActionButton fab_rutin;
    Spinner spinnerBulan;
    Button btnSimpan, btnHapus;
    TextInputEditText etJenisAkun, etSaldo, etTanggalTabungan;
    RecyclerView rvRutin;
    long tgl;
    List<Rutin> listRutin;
    RutinAdapter adapter;
    TextView tvTotalPerbulan;

    int a;

    int idForUpdate;
    boolean isUpdate;

    double totalPerbulan;

    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    RutinAdapter.OnItemClickListener listener;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            a = getArguments().getInt("month");
        } catch (NullPointerException e) {
            a = 0;
        }

        totalPerbulan = 0;
        View v = inflater.inflate(R.layout.fragment_rutin_bulan, container, false);


        listener = new RutinAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Rutin rutin) {


                slideUp.animateIn();
                fab_rutin.hide();

                isUpdate = true;

                int id = Integer.parseInt(rutin.getId());
                idForUpdate = id;
                long tgl = rutin.getTimeInMilis();
                String jenis = rutin.getJenisAkun();
                String saldo = decimalFormat.format(rutin.getSaldo());
//                double saldo = rutin.getSaldo();

                etJenisAkun.setText(jenis);

                // FORMAT DATE
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(tgl);
                SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");
                Date da = c.getTime();
                String fors = simple.format(da);
                // FORMAT DATE END

                etTanggalTabungan.setText(fors);
                etSaldo.setText("Rp. " + saldo);
//                etSaldo.setText(String.valueOf(saldo));


            }
        };

        // INIT VIEW
        rvRutin = v.findViewById(R.id.rv_rutin);
        rvRutin.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRutin.setHasFixedSize(true);
        spinnerBulan = getActivity().findViewById(R.id.spinner_bulan);
        slideView = v.findViewById(R.id.slideView);
        scrim = v.findViewById(R.id.scrim);
        fab_rutin = v.findViewById(R.id.fab_rutin);

        slideUp = new SlideUp(slideView);
        slideUp.hideImmediately();
        btnSimpan = v.findViewById(R.id.btnSimpan);
        btnHapus = v.findViewById(R.id.btnHapus);

        btnSimpan.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

        etSaldo = v.findViewById(R.id.et_jumlah);

        etJenisAkun = v.findViewById(R.id.et_jenis_rutin);

        etTanggalTabungan = v.findViewById(R.id.et_tanggal_tabungan);
        etTanggalTabungan.setKeyListener(null);
        etTanggalTabungan.setOnClickListener(this);
        tvTotalPerbulan = v.findViewById(R.id.tv_total_jumlah_pebulan);


        // INIT VIEW END


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.animateOut();
                fab_rutin.show();


            }
        });

        try {

            RutinDbHelper helper = new RutinDbHelper(getContext());
            listRutin = helper.selectAll();
            List<Rutin> filterList = new ArrayList<>();
            for (Rutin rutin : listRutin) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rutin.getTimeInMilis());
                Log.v("getlongfdb", String.valueOf(rutin.getTimeInMilis()));
                int m = cal.get(Calendar.MONTH);
                if (m == 0) {
                    filterList.add(new Rutin(rutin.getId(), rutin.getTimeInMilis(), rutin.getJenisAkun(), rutin.getSaldo()));
                    totalPerbulan = totalPerbulan + rutin.getSaldo();
                }
            }


            adapter = new RutinAdapter(getContext(), listRutin, listener);
//            rvRutin.notifyAll();
            rvRutin.setAdapter(adapter);
            String total = decimalFormat.format(totalPerbulan);
            tvTotalPerbulan.setText("Rp. " + total);
//            tvTotalPerbulan.setText(String.valueOf(totalPerbulan));

            Log.v("itemCount", adapter.getItemCount() + "");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                totalPerbulan = 0;
                spinnerBulan.getSelectedItem();
//                Log.v("position", )
                rvRutin.removeAllViews();
//
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

                RutinDbHelper helper = new RutinDbHelper(getContext());
                listRutin = helper.selectAll();
                List<Rutin> asd = new ArrayList<>();
                for (Rutin rutin : listRutin) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(rutin.getTimeInMilis());
                    cal.setTimeInMillis(rutin.getTimeInMilis());

                    int y = cal.get(Calendar.YEAR);
                    int m = cal.get(Calendar.MONTH);
                    int d = cal.get(Calendar.DAY_OF_MONTH);
                    Log.v("testselect", y + " " + m + " " + d);
                    Log.v("position", m + " p " + position);
                    if (m == position) {
                        asd.add(new Rutin(rutin.getId(), rutin.getTimeInMilis(), rutin.getJenisAkun(), rutin.getSaldo()));
                        totalPerbulan = totalPerbulan + rutin.getSaldo();
                    }
                }
                adapter = new RutinAdapter(getContext(), asd, listener);
                adapter.notifyDataSetChanged();
                rvRutin.setAdapter(adapter);
                String total = decimalFormat.format(totalPerbulan);
                tvTotalPerbulan.setText("Rp. " + total);
//                tvTotalPerbulan.setText(String.valueOf(totalPerbulan));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//


        fab_rutin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "month " + a, Toast.LENGTH_SHORT).show();
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
                if (i == View.GONE) {
                    fab_rutin.show();
                    clear();
                    isUpdate = false;
                }

            }
        });


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                totalPerbulan = 0;
                long tanggalTabungan = tgl;
                String jenisAkun = etJenisAkun.getText().toString();
                double saldo = Double.parseDouble(etSaldo.getText().toString());

                RutinDbHelper helper = new RutinDbHelper(getContext());


                if (isUpdate) {
                    long time;
                    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy"); // Make sure user insert date into edittext in this format.

                    Date dateObject;

                    try {
                        String dob_var = (etTanggalTabungan.getText().toString());

                        dateObject = formatter.parse(dob_var);

                        Calendar c = Calendar.getInstance();
                        c.setTime(dateObject);
                        time = c.getTimeInMillis();
                        helper.update(idForUpdate, time, jenisAkun, saldo);

//                        date = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
//                        time = new SimpleDateFormat("h:mmaa").format(dateObject);
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Log.i("E11111111111", e.toString());
                    }


                    Toast.makeText(getContext(), "update db", Toast.LENGTH_SHORT).show();
                } else {

                    helper.insert(tanggalTabungan, jenisAkun, saldo);
                    Toast.makeText(getContext(), "insert db", Toast.LENGTH_SHORT).show();
                }

                rvRutin.removeAllViews();
                RutinDbHelper helper1 = new RutinDbHelper(getContext());
                listRutin = helper1.selectAll();
                List<Rutin> asd = new ArrayList<>();
                for (Rutin rutin : listRutin) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(rutin.getTimeInMilis());
                    Log.v("getlongfdb", String.valueOf(rutin.getTimeInMilis()));
                    int m = cal.get(Calendar.MONTH);
                    if (m == spinnerBulan.getSelectedItemPosition()) {
                        asd.add(new Rutin(rutin.getId(), rutin.getTimeInMilis(), rutin.getJenisAkun(), rutin.getSaldo()));
                        totalPerbulan = totalPerbulan + rutin.getSaldo();
                    }
                }


                adapter = new RutinAdapter(getContext(), asd, listener);
                adapter.notifyDataSetChanged();
                rvRutin.setAdapter(adapter);

                slideUp.animateOut();
                fab_rutin.show();
                String total = decimalFormat.format(totalPerbulan);
                tvTotalPerbulan.setText("Rp. " + total);
//                tvTotalPerbulan.setText(String.valueOf(totalPerbulan));

                clear();
                idForUpdate = -1;

                break;
            case R.id.btnHapus:
                clear();
                break;

            case R.id.et_tanggal_tabungan:
                Calendar cal = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));

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

//        Log.v("testinsert", String.valueOf(s.getTimeInMillis()));
//        Log.v("testinsert", String.valueOf(s.get(Calendar.MONTH)));


    }

    void clear() {
        etSaldo.setText("");
        etTanggalTabungan.setText("");
        etJenisAkun.setText("");
    }

    public Context getRutinBln() {
        return getContext();
    }

}

