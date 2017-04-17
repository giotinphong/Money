package com.example.son.money.data.tableContructor;

import java.util.Calendar;

/**
 * Created by son on 4/17/2017.
 */

public class Wallet {
    String Id_wallet,Wallet_name;
    double Budget;

    public Wallet() {
        this.Id_wallet = Calendar.getInstance().getTimeInMillis()+"";
    }

    public String getId_wallet() {
        return Id_wallet;
    }

    public void setId_wallet(String id_wallet) {
        Id_wallet = id_wallet;
    }

    public String getWallet_name() {
        return Wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        Wallet_name = wallet_name;
    }

    public double getBudget() {
        return Budget;
    }

    public void setBudget(double budget) {
        Budget = budget;
    }

    public Wallet(String wallet_name, double budget) {

        Wallet_name = wallet_name;
        Budget = budget;
    }

    public Wallet(String id_wallet, String wallet_name, double budget) {

        Id_wallet = id_wallet;
        Wallet_name = wallet_name;
        Budget = budget;
    }
}
