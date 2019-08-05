package com.example.finrecapps.Model;

public class Rutin {

    private String id;
    private long timeInMilis;
    private double totalTabungan;
    private double saldo;

    public Rutin() {
    }

    public Rutin(String id, long timeInMilis, double totalTabungan, double saldo) {
        this.id = id;
        this.timeInMilis = timeInMilis;
        this.totalTabungan = totalTabungan;
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

    public double getTotalTabungan() {
        return totalTabungan;
    }

    public void setTotalTabungan(double totalTabungan) {
        this.totalTabungan = totalTabungan;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
