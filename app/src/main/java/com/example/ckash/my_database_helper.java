package com.example.ckash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class my_database_helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Transaction_database.db";
    private static final String TABLE_NAME = "transaction_database";
    private static final int VERSION_NUMBER = 1;
    private static final String ID = "_id";
    private static final String DATE = "DATE";
    private static final String CAUSE = "CAUSE";
    private static final String AMOUNT = "AMOUNT";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATE+" VARCHAR(255),"+CAUSE+" VARCHAR(255),"+AMOUNT+" INTEGER);";

    private Context context;

    public my_database_helper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
