package com.example.finrecapps.Database;

import android.provider.BaseColumns;

public class RutinContract {

    public RutinContract(){}

    public static class RutinEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_rutin";
        public static final String COLUMN_TANGGAL_RUTIN = "tanggal_rutin";
        public static final String COLUMN_TOTAL_TABUNGAN = "total_tabungan";
        public static final String COLUMN_SALDO = "saldo";

    }
}
