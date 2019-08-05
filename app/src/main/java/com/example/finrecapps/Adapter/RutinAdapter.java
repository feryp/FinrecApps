package com.example.finrecapps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finrecapps.Model.Rutin;
import com.example.finrecapps.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RutinAdapter extends RecyclerView.Adapter<RutinAdapter.RutinViewHolder> {

    Context c;
    List<Rutin> rutinList;


    public RutinAdapter(Context c, List<Rutin> rutinList) {
        this.c = c;
        this.rutinList = rutinList;
    }

    @NonNull
    @Override
    public RutinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(c).inflate(R.layout.list_item_rutin, null, false);
        return new RutinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinViewHolder rutinViewHolder, int i) {
        Rutin rutinModel = rutinList.get(i);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(rutinModel.getTimeInMilis());
        // Creating date format
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");

        // Creating date from milliseconds
        // using Date() constructor
        Date da = c.getTime();



        // Formatting Date according to the
        // given format
        System.out.println(simple.format(da));

        String fors = simple.format(da);
        rutinViewHolder.tvTanggal.setText(fors);
        rutinViewHolder.tvTotalTabungan.setText(String.valueOf(rutinModel.getTotalTabungan()));
        rutinViewHolder.tvSaldo.setText(String.valueOf(rutinModel.getSaldo()));

    }

    @Override
    public int getItemCount() {
        return rutinList.size();
    }

    public class RutinViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTotalTabungan, tvSaldo, tvTanggal;

        public RutinViewHolder(@NonNull View v) {
            super(v);

            tvTotalTabungan = v.findViewById(R.id.tv_nilai_total_tabungan);
            tvSaldo = v.findViewById(R.id.tv_nilai_saldo);
            tvTanggal = v.findViewById(R.id.tv_waktu);

        }

        public TextView getTvTotalTabungan() {
            return tvTotalTabungan;
        }

        public void setTvTotalTabungan(TextView tvTotalTabungan) {
            this.tvTotalTabungan = tvTotalTabungan;
        }

        public TextView getTvSaldo() {
            return tvSaldo;
        }

        public void setTvSaldo(TextView tvSaldo) {
            this.tvSaldo = tvSaldo;
        }

        public TextView getTvTanggal() {
            return tvTanggal;
        }

        public void setTvTanggal(TextView tvTanggal) {
            this.tvTanggal = tvTanggal;
        }


    }
}
