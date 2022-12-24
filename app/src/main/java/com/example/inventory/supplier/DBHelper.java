package com.example.inventory.supplier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_SUPPLIERS = "data_inventori";
    public static final String COLUMN_ID = "id_supplier";
    public static final String COLUMN_NAME = "nama_supplier";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NOHP = "nohp";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String db_name = "inventori.db";
    public static final int db_version = 1;

    //    query SQL for create table
    public static final String db_create = "create table "+
            TABLE_SUPPLIERS + "("+
            COLUMN_ID + " integer primary key autoincrement, "+
            COLUMN_NAME + " varchar(50) not null, "+
            COLUMN_EMAIL + " varchar(50) not null, "+
            COLUMN_NOHP + " varchar(50) not null, "+
            COLUMN_ALAMAT + " varchar(100) not null);";

    public DBHelper(Context context){
        super(context, db_name, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SUPPLIERS);
        onCreate(db);
    }
}
