package com.example.finrecapps.Model;

public class Rutin {

    private String id;
    private long timeInMilis;
    private String jenisAkun;
    private double saldo;

    public Rutin() {
    }

    public Rutin(String id, long timeInMilis, String jenisAkun, double saldo) {
        this.id = id;
        this.timeInMilis = timeInMilis;
        this.jenisAkun = jenisAkun;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeInMilis() {
        return timeInMilis;
    }

    public void setTimeInMilis(long timeInMilis) {
        this.timeInMilis = timeInMilis;
    }

    public String getJenisAkun() {
        return jenisAkun;
    }

    public void setJenisAkun(String jenisAkun) {
        this.jenisAkun = jenisAkun;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
