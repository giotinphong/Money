package com.example.son.money.data.tableContructor;

import android.location.Location;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by son on 4/17/2017.
 */

public class Transaction {
    String Id_trans;
    String Id_cate;
    String Note;
    String Id_wallet;
    String Id_event;
    String Remind;
    double Amount;
    long Time;
    boolean IsIncome; // thu nhap or chi tieu
    double La_Location,Lo_Location;

    public Transaction() {
        IsIncome = false;
        this.Id_trans = Calendar.getInstance().getTimeInMillis()+"";
    }

    public Transaction(String id_trans, String id_cate, String id_wallet, double amount, long time,Boolean isIncome) {
        this.Id_trans = id_trans;
        this.Id_cate = id_cate;
        this.Id_wallet = id_wallet;
        this.Amount = amount;
        this.Time = time;
        this.IsIncome = isIncome;
    }

    public Transaction(String id_cate, String id_wallet, double amount, long time, boolean isIncome) {
        this.Id_cate = id_cate;
        this.Id_wallet = id_wallet;
        this.Amount = amount;
        this.Time = time;
        this.IsIncome = isIncome;
        this.Id_trans = Calendar.getInstance().getTimeInMillis()+"";
    }
    public String getId_trans() {
        return Id_trans;
    }

    public void setId_trans(String id_trans) {
        Id_trans = id_trans;
    }

    public String getId_cate() {
        return Id_cate;
    }

    public void setId_cate(String id_cate) {
        Id_cate = id_cate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getId_wallet() {
        return Id_wallet;
    }

    public void setId_wallet(String id_wallet) {
        Id_wallet = id_wallet;
    }

    public String getId_event() {
        return Id_event;
    }

    public void setId_event(String id_event) {
        Id_event = id_event;
    }

    public String getRemind() {
        return Remind;
    }

    public void setRemind(String remind) {
        Remind = remind;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public Transaction(String id_trans, String id_cate, String note, String id_wallet, String id_event, String remind, double amount, long time, boolean isIncome, double la_Location, double lo_Location) {
        Id_trans = id_trans;
        Id_cate = id_cate;
        Note = note;
        Id_wallet = id_wallet;
        Id_event = id_event;
        Remind = remind;
        Amount = amount;
        Time = time;
        IsIncome = isIncome;
        La_Location = la_Location;
        Lo_Location = lo_Location;
    }

    public boolean isIncome() {
        return IsIncome;
    }

    public void setIncome(boolean income) {
        IsIncome = income;
    }

    public double getLa_Location() {
        return La_Location;
    }

    public void setLa_Location(double la_Location) {
        La_Location = la_Location;
    }

    public double getLo_Location() {
        return Lo_Location;
    }

    public void setLo_Location(double lo_Location) {
        Lo_Location = lo_Location;
    }
}
