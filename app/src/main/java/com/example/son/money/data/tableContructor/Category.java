package com.example.son.money.data.tableContructor;

/**
 * Created by son on 4/17/2017.
 */

public class Category {
    String Id_cate,Category_name,Id_dad_cate;
    Boolean IsIncome;

    public Category() {
    }

    public String getId_cate() {
        return Id_cate;
    }

    public void setId_cate(String id_cate) {
        Id_cate = id_cate;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public String getId_dad_cate() {
        return Id_dad_cate;
    }

    public void setId_dad_cate(String id_dad_cate) {
        Id_dad_cate = id_dad_cate;
    }

    public Boolean getIncome() {
        return IsIncome;
    }

    public void setIncome(Boolean income) {
        IsIncome = income;
    }
}
