package com.example.inventory.elektronik;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelperElektronik extends SQLiteOpenHelper {
    public static final String TABLE_ELECTRONICS = "tb_electronic";
    public static final String COL_ID_ELECTRONIC = "id_electronic";
    public static final String COL_NAME_ELECTRONIC = "name_electronic";
    public static final String COL_HARGA_BELIE = "harga_beli";
    public static final String COL_HARGA_JUALE = "harga_jual";
    public static final String COL_STOCKE = "stock";
    public static final String COL_DESCRIPTIONE = "description";
    public static final String db_name = "db_inventory";
    public static final int db_version = 1;

    //    query SQL for create table
    public static final String db_create = "create table "+
            TABLE_ELECTRONICS+ " (" +
            COL_ID_ELECTRONIC + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME_ELECTRONIC + " VARCHAR(50) NOT NULL, " +
            COL_HARGA_BELIE + " VARCHAR(50) NOT NULL, " +
            COL_HARGA_JUALE + " VARCHAR(50) NOT NULL, " +
            COL_STOCKE + " VARCHAR(50) NOT NULL, " +
            COL_DESCRIPTIONE + " VARCHAR(50) NOT NULL );";

    public DataBaseHelperElektronik(Context context){
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
        Log.w(DataBaseHelperElektronik.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ELECTRONICS);
        onCreate(db);
    }
}
