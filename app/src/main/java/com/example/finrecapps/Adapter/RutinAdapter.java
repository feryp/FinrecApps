package com.example.finrecapps.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finrecapps.Database.RutinDbHelper;
import com.example.finrecapps.Fragment.RutinBulanFragment;
import com.example.finrecapps.MainActivity;
import com.example.finrecapps.Model.Rutin;
import com.example.finrecapps.PeriodeBulanActivity;
import com.example.finrecapps.PeriodeTahunActivity;
import com.example.finrecapps.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RutinAdapter extends RecyclerView.Adapter<RutinAdapter.RutinViewHolder> {


    private Context c;
    private List<Rutin> rutinList;
    private final OnItemClickListener listener;

    private int position;



    public RutinAdapter(Context c, List<Rutin> rutinList, OnItemClickListener listener) {
        this.c = c;
        this.rutinList = rutinList;
        this.listener = listener;
    }

    public RutinAdapter(Context c, List<Rutin> rutinList) {
        this.c = c;
        this.rutinList = rutinList;
        this.listener = null;
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
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

        // Formatting Date according to the
        // given format
        System.out.println(simple.format(da));
        String as = decimalFormat.format(rutinList.get(i).getSaldo());

        String fors = simple.format(da);
        rutinViewHolder.tvTanggal.setText(fors);
        rutinViewHolder.tvJenisAkun.setText(rutinModel.getJenisAkun());
        rutinViewHolder.tvSaldo.setText("Rp. " + as);
//        rutinViewHolder.tvSaldo.setText(String.valueOf(rutinModel.getSaldo()));
        rutinViewHolder.bind(rutinList.get(i), listener);




    }

    public Rutin getRutin(){
        return rutinList.get(position);
    }

    @Override
    public int getItemCount() {
        return rutinList.size();
    }

    public class RutinViewHolder extends RecyclerView.ViewHolder implements  View.OnLongClickListener {

        private TextView tvJenisAkun, tvSaldo, tvTanggal;

        Rutin rutin;

        public RutinViewHolder(@NonNull View v) {
            super(v);

            v.setOnLongClickListener(this);
            tvJenisAkun = v.findViewById(R.id.tv_isi_rutin);
            tvSaldo = v.findViewById(R.id.tv_jumlah_rutin);
            tvTanggal = v.findViewById(R.id.tv_waktu);

        }



        public void bind(final Rutin rutin, final OnItemClickListener listener) {
            this.rutin = rutin;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        listener.onItemClick(v, rutin);
                    }catch(NullPointerException ex){
                    }
                }
            });
        }




        @Override
        public boolean onLongClick(View v) {

            Activity as = new PeriodeBulanActivity();
            Activity context = (Activity)itemView.getContext();

            String bulan = as.toString().substring(0, as.toString().length() - 8);
            String current = context.toString().substring(0, context.toString().length() - 8);

            Log.v("context123", context.toString() + " " + as.toString());

            if(!current.equals(bulan)){

            }else{
                showDialog();
            }
            return false;
        }

        void showDialog() {
            AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
            alertDialog.setTitle("Hapus Data");
            alertDialog.setMessage("Apakah anda ingin menghapus data ini ? ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            RutinDbHelper helper = new RutinDbHelper(itemView.getContext());

                            int deletedRows = helper.delete(Integer.valueOf(rutin.getId()));


                            Toast.makeText(itemView.getContext(), deletedRows + " item terhapus", Toast.LENGTH_SHORT).show();
                            Context context = itemView.getContext();
                            ((PeriodeBulanActivity) context).finish();
                            context.startActivity(new Intent(context, PeriodeBulanActivity.class));
                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, Rutin rutin);
    }



}
