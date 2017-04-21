package com.example.son.money.data.tableContructor;

import java.util.Calendar;

/**
 * Created by son on 4/17/2017.
 */

public class Category {
    long Id_cate,Id_dad_cate;
    String Category_name;
    Boolean IsIncome;

    public Category() {
        this.Id_cate = Calendar.getInstance().getTimeInMillis();
        this.Id_dad_cate = 0;
    }

    public long getId_cate() {
        return Id_cate;
    }

    public void setId_cate(long id_cate) {
        Id_cate = id_cate;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public long getId_dad_cate() {
        return Id_dad_cate;
    }

    public void setId_dad_cate(long id_dad_cate) {
        Id_dad_cate = id_dad_cate;
    }

    public Boolean getIncome() {
        return IsIncome;
    }

    public void setIncome(Boolean income) {
        IsIncome = income;
    }
}
