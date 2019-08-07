package com.example.finrecapps.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.finrecapps.Adapter.RutinAdapter;
import com.example.finrecapps.Database.RutinContract.RutinEntry;
import com.example.finrecapps.Model.Rutin;

import java.util.ArrayList;
import java.util.List;

public class RutinDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_keuangan.db";
    public static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATE_DATABASE = "CREATE TABLE "
            + RutinEntry.TABLE_NAME + " ("
            + RutinEntry._ID + " INTEGER PRIMARY KEY, "
            + RutinEntry.COLUMN_TANGGAL_RUTIN + " INTEGER, "
            + RutinEntry.COLUMN_JENIS_AKUN + " TEXT, "
            + RutinEntry.COLUMN_SALDO + " REAL)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + RutinEntry.TABLE_NAME;

    public RutinDbHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public long insert(long tanggalRutinInMilis, String jenisAkun, double saldo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RutinEntry.COLUMN_TANGGAL_RUTIN, tanggalRutinInMilis);
        values.put(RutinEntry.COLUMN_JENIS_AKUN, jenisAkun);
        values.put(RutinEntry.COLUMN_SALDO, saldo);

        Log.v("INSERT", tanggalRutinInMilis + " " + jenisAkun + " " + saldo);


        return db.insertOrThrow(RutinEntry.TABLE_NAME, null, values);
    }

    public List selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RutinEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        List<Rutin> rutinList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String id =cursor.getString(cursor.getColumnIndexOrThrow(RutinEntry._ID));
            long tanggal = cursor.getLong(cursor.getColumnIndexOrThrow(RutinEntry.COLUMN_TANGGAL_RUTIN));
            String jenisAkun = cursor.getString(cursor.getColumnIndexOrThrow(RutinEntry.COLUMN_JENIS_AKUN));
            double saldo = cursor.getDouble(cursor.getColumnIndexOrThrow(RutinEntry.COLUMN_SALDO));

            Rutin r = new Rutin(id,
                    tanggal,
                    jenisAkun,
                    saldo
            );

            Log.v("selectAll()",id + " "+ tanggal + " " + jenisAkun + " "+ saldo);
            rutinList.add(r);
        }

        cursor.close();

        return new ArrayList<>(rutinList);

    }

    public int delete(int id) {

        SQLiteDatabase db = this.getReadableDatabase();


        // Define 'where' part of query.
        String selection = RutinEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(id)};
        // Issue SQL statement.
        return db.delete(RutinEntry.TABLE_NAME, selection, selectionArgs);
    }

    public int update(int id, long tanggalRutinInMilis, String jenisAkun, double saldo){

        SQLiteDatabase db = this.getWritableDatabase();

        // Which row to update, based on the title
        String selection = RutinEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // New value for one column
        ContentValues values = new ContentValues();

        values.put(RutinEntry.COLUMN_TANGGAL_RUTIN, tanggalRutinInMilis);
        values.put(RutinEntry.COLUMN_JENIS_AKUN, jenisAkun);
        values.put(RutinEntry.COLUMN_SALDO, saldo);


        return db.update(
                RutinEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

//    public List selectSpecificMonth(String idxMonth){
//        SQLiteDatabase db = this.getReadableDatabase();
//
////        String[] selectionMonth = {month};
////        int mo = idxMonth + 1;
////        String as = "SELECT strftime(%m, datetime(table_name, unixepoch)) as d, "
////                +RutinEntry.COLUMN_TANGGAL_RUTIN+"";
//
//        StringBuilder sql = new StringBuilder("SELECT").append(" ")
//                .append("strftime('%m', datetime("+RutinEntry.COLUMN_TANGGAL_RUTIN+", 'unixepoch')) as month,")
//                .append(RutinEntry.COLUMN_TOTAL_TABUNGAN+",")
//                .append(RutinEntry.COLUMN_SALDO).append(" ")
//                .append("FROM "+RutinEntry.TABLE_NAME).append(" ")
//                .append("WHERE month like ? ;");
//
//        Cursor cursor = db.rawQuery(sql.toString(), new String[]{idxMonth});
//
//
//        List<Rutin> rutinList = new ArrayList<>();
//
//        while(cursor.moveToNext()){
//            Rutin r = new Rutin(cursor.getString(cursor.getColumnIndexOrThrow(RutinEntry._ID)),
//                    cursor.getString(cursor.getColumnIndexOrThrow("month")),
//                    cursor.getDouble(cursor.getColumnIndexOrThrow(RutinEntry.COLUMN_TOTAL_TABUNGAN)),
//                    cursor.getDouble(cursor.getColumnIndexOrThrow(RutinEntry.COLUMN_SALDO))
//            );
//
//            rutinList.add(r);
//        }
//
//        cursor.close();
//
//        return rutinList;
//    }
}

