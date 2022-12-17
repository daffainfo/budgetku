package com.daffainfo.budgetku.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daffainfo.budgetku.model.Budget;

import java.util.ArrayList;

public class BudgetRepository {
    public static BudgetRepository instance = null;
    private SQLiteOpenHelper openDb;
    private final SQLiteDatabase database;

    public BudgetRepository(Context context) {
        this.openDb = new SQLiteOpenHelper(context, "db.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };

        // type == pemasukan / pengeluaran
        database = openDb.getWritableDatabase();
//        database.execSQL("DROP TABLE IF EXISTS budget");
        database.execSQL("CREATE TABLE IF NOT EXISTS budget (" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "name varchar(255) NOT NULL," +
                "categories varchar(25) NOT NULL," +
                "money varchar(255) NOT NULL," +
                "description varchar(500) NOT NULL," +
                "type varchar(10) NOT NULL," +
                "datetime varchar(20) NOT NULL)"
        );
    }

    public static BudgetRepository getInstance(Context context) {
        if (instance == null)
            instance = new BudgetRepository(context);

        return instance;
    }

    //Buat floating button action pemasukan + pengeluaran
    public void create(Budget budget) {
        ContentValues newData = new ContentValues();

        newData.put("name", budget.getName());
        newData.put("categories", budget.getCategories());
        newData.put("money", budget.getMoney());
        newData.put("description", budget.getDescription());
        newData.put("type", budget.getType());
        newData.put("datetime", budget.getDatetime());

        database.insert("budget", null, newData);
    }

    //Buat dapet total
    public double count(String type) {
        double totalIncome = 0;
        Cursor cursor = database.rawQuery("SELECT SUM(money) FROM budget WHERE type = \"" + type + "\";", null);

        if (cursor.moveToFirst()) {
            do {
                totalIncome += cursor.getDouble(0);
            } while (cursor.moveToNext());
        }

        return totalIncome;
    }

    //Buat cek detail di laman history
    public Budget get(int id) {
        Cursor cursor = database.rawQuery("SELECT * FROM budget where id = " + id + ";", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new Budget(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        }

        return null;
    }

    //Buat search anggaran beredasarkan nama di laman history
    public ArrayList<Budget> search(String name) {
        Cursor cursor = database.rawQuery("SELECT * FROM budget where name like '%" + name + "%' order by name", null);

        ArrayList<Budget> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                result.add(new Budget(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        return result;
    }

    public double budgetDate(String type, String date) {
        double total = 0;
        Cursor cursor = database.rawQuery("SELECT SUM(money) FROM budget WHERE datetime like '%" + date + "%' AND type = \"" + type + "\";", null);

        if (cursor.moveToFirst()) {
            do {
                total += cursor.getDouble(0);
            } while (cursor.moveToNext());
        }

        return total;
    }

    // Buat dapat total income tanggal tertentu
    public ArrayList<Budget> topCategories(String type) {
        Cursor cursor = database.rawQuery("SELECT categories, SUM(money) as total_spent FROM budget WHERE type = \"" + type + "\" GROUP BY categories ORDER BY total_spent DESC", null);

        ArrayList<Budget> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                result.add(new Budget(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        return result;
    }

    //Buat listview di laman history
    public ArrayList<Budget> read() {
        Cursor cursor = database.rawQuery("SELECT * FROM budget order by datetime(datetime) DESC", null);

        ArrayList<Budget> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                result.add(new Budget(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        return result;
    }
}