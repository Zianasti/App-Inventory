package com.example.inventory.fashion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperFashion extends SQLiteOpenHelper {
    public static final String TABLE_FASHIONS = "tb_fashion";
    public static final String COL_ID_FASHION = "id_fashion";
    public static final String COL_NAME_FASHION = "name_fashion";
    public static final String COL_HARGA_BELIF = "harga_beli";
    public static final String COL_HARGA_JUALF = "harga_jual";
    public static final String COL_STOCKF = "stock";
    public static final String COL_DESCRIPTIONF = "description";
    public static final String db_name = "db_inventory";
    public static final int db_version = 1;

    //    query SQL for create table
    public static final String db_create = "create table "+
            TABLE_FASHIONS+ " (" +
            COL_ID_FASHION + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME_FASHION + " VARCHAR(50) NOT NULL, " +
            COL_HARGA_BELIF + " VARCHAR(50) NOT NULL, " +
            COL_HARGA_JUALF + " VARCHAR(50) NOT NULL, " +
            COL_STOCKF + " VARCHAR(50) NOT NULL, " +
            COL_DESCRIPTIONF + " VARCHAR(50) NOT NULL );";

    public DatabaseHelperFashion(Context context){
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
        Log.w(DatabaseHelperFashion.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FASHIONS);
        onCreate(db);
    }
}
