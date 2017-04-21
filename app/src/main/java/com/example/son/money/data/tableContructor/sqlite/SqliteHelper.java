package com.example.son.money.data.tableContructor.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;

import com.example.son.money.data.tableContructor.Category;
import com.example.son.money.data.tableContructor.Transaction;
import com.example.son.money.data.tableContructor.Wallet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by son on 4/17/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Money";

    private static final String TABLE_CATE = "category";
    private static final String CATE_ID = "Id_cate";
    private static final String CATE_NAME = "Category_name";
    private static final String CATE_DAD_CATE = "Id_dad_cate";
    private static final String CATE_IS_INCOME = "IsIncome";

    private static final String TABLE_TRANS = "trans";
    private static final String TRANS_ID = "Id_trans";
    private static final String TRANS_NOTE = "Note";
    private static final String TRANS_ID_EVENT = "Id_event";
    private static final String TRANS_REMIND = "Remind";
    private static final String TRANS_DATE = "Time";
    private static final String TRANS_AMOUNT = "Amount";
    private static final String TRANS_LOCATION_LA = "La_location";
    private static final String TRANS_LOCATION_LO = "Lo_location";

    private static final String TABLE_WALL = "wallet";
    private static final String WALL_ID = "Id_wallet";
    private static final String WALL_NAME = "Wallet_name";
    private static final String WALL_BUDGET = "Budget";
    private  SQLiteDatabase db;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TRANSACTION
        db.execSQL("create table IF NOT EXISTS " + TABLE_TRANS + "(" +
                TRANS_ID + " NVARCHAR(50) PRIMARY KEY," + CATE_ID + " NVARCHAR(50) NOT NULL,"
                + TRANS_NOTE + " nvarchar(50)," + WALL_ID + " NVARCHAR(50) NOT NULL," + TRANS_ID_EVENT +
                " NVARCHAR(50)," + TRANS_REMIND + " nvarchar(50),"
                + TRANS_AMOUNT + " DOUBLE NOT NULL," + TRANS_DATE + " LONG NOT NULL," + CATE_IS_INCOME + " BOOLEAN NOT NULL,"
                + TRANS_LOCATION_LA + " LONG,"
                + TRANS_LOCATION_LO + " LONG)");

        //create category
        db.execSQL("create table IF NOT EXISTS " + TABLE_CATE + "(" + CATE_ID
                + " NVARCHAR(50) PRIMARY KEY," + CATE_NAME + " NVARCHAR(50) NOT NULL,"
                + CATE_DAD_CATE + " NVARCHAR(50) NOT NULL,"+
                CATE_IS_INCOME + " BOOLEAN NOT NULL )");

        //create wallet
        db.execSQL("create table IF NOT EXISTS " + TABLE_WALL + "(" + WALL_ID + " NVARCHAR(50) PRIMARY KEY,"
                + WALL_NAME + " NVARCHAR(50) NOT NULL," + WALL_BUDGET + " DOUBLE NOT NULL)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WALL);
        // Create tables again
        onCreate(db);
    }

    //---------------* setup for category *---------------\\

    //ON category
    public String addCategory(Category category) {
         db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATE_ID, category.getId_cate());
        values.put(CATE_DAD_CATE, category.getId_dad_cate());
        values.put(CATE_NAME, category.getCategory_name());
        values.put(CATE_IS_INCOME, category.getIncome());
        db.insert(TABLE_CATE, null, values);
        db.close();
        return "OK";
    }

    //getcategory dad
    public Category getCategory(String id) {
         db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CATE, new String[]{CATE_ID, CATE_NAME, CATE_DAD_CATE, CATE_IS_INCOME}
                , CATE_ID + "=?", new String[]{id}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Category category = new Category();
        category.setId_cate(Long.parseLong(cursor.getString(0)));
        category.setCategory_name(cursor.getString(1));
        category.setId_dad_cate(Long.parseLong(cursor.getString(2)));
        category.setIncome(Boolean.valueOf(cursor.getString(3)));
        return category;
    }


    //get all category dad(){
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categoryArrayList  = new ArrayList<>();
        String query = "select * from "+TABLE_CATE;
         db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Category category = new Category();
                category.setId_cate(Long.parseLong(cursor.getString(0)));
                category.setCategory_name(cursor.getString(1));
                category.setId_dad_cate(Long.parseLong(cursor.getString(2)));
                category.setIncome(Boolean.valueOf(cursor.getString(3)));
                categoryArrayList.add(category);
            }while (cursor.moveToNext());
        }
        db.close();
        return categoryArrayList;
    }
    //get all category son
    public ArrayList<Category> getAllCategorySon(String id){
        ArrayList<Category> categoryArrayList  = new ArrayList<>();
        String query = "select * from "+TABLE_CATE+"where "+CATE_DAD_CATE+" = '"+id+"'";
         db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Category category = new Category();
                category.setId_cate(Long.parseLong(cursor.getString(0)));
                category.setCategory_name(cursor.getString(1));
                category.setId_dad_cate(Long.parseLong(cursor.getString(2)));
                category.setIncome(Boolean.valueOf(cursor.getString(3)));
                categoryArrayList.add(category);
            }while (cursor.moveToNext());
        }
        db.close();
        return categoryArrayList;
    }
    //get count
    public int getCategoryCount(){
        String countQuery = "SELECT  * FROM " + TABLE_CATE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
    //update category
    public int updateCategory(Category category){
         db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATE_DAD_CATE, category.getId_dad_cate());
        values.put(CATE_NAME, category.getCategory_name());
        values.put(CATE_IS_INCOME, category.getIncome());
        db.insert(TABLE_CATE, null, values);

        // updating row
        return db.update(TABLE_CATE, values, CATE_ID + " = ?",
                new String[] { String.valueOf(category.getId_cate()) });
    }
    //delete category
    public  void deleteCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATE,CATE_ID+"=?",new String[]{String.valueOf(category.getId_cate())});
        db.close();
    }
    //--------------------------* setup for wallet *-------------------------------------------------------------------------//
    //add wallet
    public void addWallet(Wallet wallet){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WALL_ID,wallet.getId_wallet());
        values.put(WALL_NAME,wallet.getWallet_name());
        values.put(WALL_BUDGET,wallet.getBudget());
        db.insert(TABLE_WALL,null,values);
        db.close();
    }
    //get wallet by id
    public Wallet getWallet(String id){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_WALL,new String[]{WALL_ID,WALL_NAME,WALL_BUDGET},WALL_ID+"=?",new String[]{id},null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        Wallet wallet = new Wallet();
        wallet.setId_wallet(cursor.getString(0));
        wallet.setWallet_name(cursor.getString(1));
        wallet.setBudget(Double.parseDouble(cursor.getString(1)));
        return  wallet;
    }
    //get all wallet
    public ArrayList<Wallet> getAllWallet(){
       ArrayList<Wallet> walletArrayList = new ArrayList<>();
        String query = "select * from "+TABLE_WALL;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Wallet wallet = new Wallet();
                wallet.setId_wallet(cursor.getString(0));
                wallet.setWallet_name(cursor.getString(1));
                wallet.setBudget(Double.parseDouble(cursor.getString(2)));
                walletArrayList.add(wallet);
            }while (cursor.moveToNext());
        }
        return walletArrayList;
    }
    //update wallet
    public int updateWallet(Wallet wallet){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WALL_ID,wallet.getId_wallet());
        values.put(WALL_NAME,wallet.getWallet_name());
        values.put(WALL_BUDGET,wallet.getBudget());
        return db.update(TABLE_WALL,values,WALL_ID+"=?",new String[]{wallet.getId_wallet()});
    }
    //delete wallet
    public void deleteWallet(Wallet wallet){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WALL,WALL_ID+"=?",new String[]{wallet.getId_wallet()});
        db.close();
    }
    //------------------* setup for transaction *--------------\\
//     db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TRANS + "(" +
//    TRANS_ID + " NVARCHAR(MAX) PRIMARY KEY, " + CATE_ID + " NVARCHAR(MAX), " + TRANS_NOTE + " TEXT," + WALL_ID + " NVARCHAR(MAX)," + TRANS_ID_EVENT +
//            "NVARCHAR(MAX)," + TRANS_REMIND + " TEXT, " + TRANS_AMOUNT + " DOUBLE," + TRANS_DATE + " DATETIME," + CATE_IS_INCOME + " BOOLEAN," + TRANS_LOCATION_LA + " LONG,"
//            + TRANS_LOCATION_LO + " LONG)");
    //add trans
    public void addTransaction(Transaction transaction){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANS_ID,transaction.getId_trans());
        values.put(CATE_ID,transaction.getId_cate());
        values.put(TRANS_NOTE,transaction.getNote());
        values.put(WALL_ID,transaction.getId_wallet());
        values.put(TRANS_ID_EVENT,transaction.getId_event());
        values.put(TRANS_REMIND,transaction.getRemind());
        values.put(TRANS_AMOUNT,transaction.getAmount());
        values.put(TRANS_DATE,transaction.getTime());
        values.put(CATE_IS_INCOME,transaction.isIncome());
        values.put(TRANS_LOCATION_LA,transaction.getLa_Location());
        values.put(TRANS_LOCATION_LO,transaction.getLo_Location());
        db.insert(TABLE_TRANS,null,values);
        db.close();
    }
    // get transaction
    public Transaction getTransaction(long id){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TRANS,new String[]{TRANS_ID,CATE_ID,TRANS_NOTE,WALL_ID,TRANS_ID_EVENT,
                TRANS_REMIND,TRANS_AMOUNT,TRANS_DATE,CATE_IS_INCOME,TRANS_LOCATION_LA,TRANS_LOCATION_LO},TRANS_ID+"=?",new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        Transaction transaction = new Transaction(Long.parseLong(cursor.getString(0)),
                Long.parseLong(cursor.getString(1)),cursor.getString(2),Long.parseLong(cursor.getString(3)),
                Long.parseLong(cursor.getString(4)),cursor.getString(5),Double.parseDouble(cursor.getString(6)),
                Long.parseLong(cursor.getString(7)),Boolean.parseBoolean(cursor.getString(8)),
                Double.parseDouble(cursor.getString(9)),Double.parseDouble(cursor.getString(10)));
        db.close();
        return transaction;
    }
    //TODO: get all transaction of this month
    public void test(){
        Date date = Calendar.getInstance().getTime();
        long time = date.getMonth();
        System.out.print(time);
    }
}
