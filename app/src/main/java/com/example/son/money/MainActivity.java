package com.example.son.money;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.son.money.data.tableContructor.Category;
import com.example.son.money.data.tableContructor.Transaction;
import com.example.son.money.data.tableContructor.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button)findViewById(R.id.activity_main_btn_test);
        final  TextView dis = (TextView) findViewById(R.id.activity_main_txt_test);
       final SqliteHelper db = new SqliteHelper(getApplicationContext());
        final ArrayList<Category> disCate = db.getAllCategory();
        String s ="";
        for(Category category1: disCate)
            s+= category1.getCategory_name()+" "+category1.getId_cate()+"\n";
        dis.setText(s);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Category category = new Category();
                Date now = java.util.Calendar.getInstance().getTime();
                category.setId_cate(now.getTime());
                category.setCategory_name("An uong");
                category.setId_dad_cate(0);
                category.setIncome(false);
                db.addCategory(category);

//                ArrayList<Category> disCate = db.getAllCategory();
//                String s ="";
//                for(Category category1: disCate)
//                    s+= category1.getCategory_name()+" "+category1.getId_cate()+"\n";
//                dis.setText(s);
//
                Transaction transaction = new Transaction();
                transaction.setId_trans(java.util.Calendar.getInstance().getTimeInMillis());
                transaction.setId_cate(now.getTime());
                transaction.setAmount(20000);
                transaction.setIncome(false);
                transaction.setId_wallet(0);
                transaction.setTime(now.getTime());
                db.addTransaction(transaction);
                Transaction tran1 = db.getTransaction(transaction.getId_trans());
                String s ="";
                s+="\n"+tran1.getAmount();
                dis.setText(s);

            }


        });
    }
}
