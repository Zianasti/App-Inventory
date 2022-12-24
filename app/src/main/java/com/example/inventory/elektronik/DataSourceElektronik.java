package com.example.inventory.elektronik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;

public class DataSourceElektronik {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DataBaseHelperElektronik
    private DataBaseHelperElektronik dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DataBaseHelperElektronik.COL_ID_ELECTRONIC,
            DataBaseHelperElektronik.COL_NAME_ELECTRONIC,
            DataBaseHelperElektronik.COL_STOCKE,DataBaseHelperElektronik.COL_HARGA_BELIE,DataBaseHelperElektronik.COL_HARGA_JUALE,DataBaseHelperElektronik.COL_DESCRIPTIONE};

    //DBHelper diinstantiasi pada constructor
    public DataSourceElektronik(Context context)
    {
        dbHelper = new DataBaseHelperElektronik(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert elektronik ke database
    public Elektronik createElektronik(String name, String stok , String harga_beli, String harga_jual, String description) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DataBaseHelperElektronik.COL_NAME_ELECTRONIC, name);
        values.put(DataBaseHelperElektronik.COL_STOCKE, stok);
        values.put(DataBaseHelperElektronik.COL_HARGA_BELIE, harga_beli);
        values.put(DataBaseHelperElektronik.COL_HARGA_JUALE, harga_jual);
        values.put(DataBaseHelperElektronik.COL_DESCRIPTIONE, description);

        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DataBaseHelperElektronik.TABLE_ELECTRONICS, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DataBaseHelperElektronik.TABLE_ELECTRONICS, allColumns, DataBaseHelperElektronik.COL_ID_ELECTRONIC + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek elektronik
        Elektronik newElektronik = cursorToElektronik(cursor);

        // close cursor
        cursor.close();

        // mengembalikan fashion baru
        return newElektronik;
    }
    private Elektronik cursorToElektronik(Cursor cursor){
        Elektronik elektronik = new Elektronik();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4));

        elektronik.setId(cursor.getLong(0));
        elektronik.setNama_elektronik(cursor.getString(1));
        elektronik.setStok(cursor.getString(2));
        elektronik.setHarga_beli(cursor.getString(3));
        elektronik.setHarga_jual(cursor.getString(4));
        elektronik.setDescription(cursor.getString(5));

        return elektronik;
    }

    public ArrayList<Elektronik> getAllElektronik() {
        ArrayList<Elektronik> daftarElektronik = new
                ArrayList<Elektronik>();
        // select all SQL query
        Cursor cursor =
                database.query(DataBaseHelperElektronik.TABLE_ELECTRONICS,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data elektronik ke
        // daftar elektronik
        while (!cursor.isAfterLast()) {
            Elektronik elektronik = cursorToElektronik(cursor);
            daftarElektronik.add(elektronik);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarElektronik;
    }
    //ambil satu elektronik sesuai id
    public Elektronik getElektronik(long id){
        Elektronik elektronik = new Elektronik();

        Cursor cursor = database.query(DataBaseHelperElektronik.TABLE_ELECTRONICS, allColumns, "id_electronic="+id, null, null, null, null);

        cursor.moveToFirst();
        elektronik = cursorToElektronik(cursor);
        cursor.close();
        return elektronik;
    }
    //update elektronik yang diedit
    public void updateElektronik(Elektronik e)
    {
        //ambil id elektronik
        String strFilter = "id_electronic=" + e.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DataBaseHelperElektronik.COL_NAME_ELECTRONIC,
                e.getNama_elektronik());
        args.put(DataBaseHelperElektronik.COL_STOCKE,
                e.getStok());
        args.put(DataBaseHelperElektronik.COL_HARGA_BELIE,
                e.getHarga_beli());
        args.put(DataBaseHelperElektronik.COL_HARGA_JUALE,
                e.getHarga_jual());
        args.put(DataBaseHelperElektronik.COL_DESCRIPTIONE,
                e.getDescription());
        //update query
        database.update(DataBaseHelperElektronik.TABLE_ELECTRONICS, args,
                strFilter, null);
    }
    // delete elektronik sesuai ID
    public void deleteElektronik(long id)
    {
        String strFilter = "id_electronic=" + id;
        database.delete(DataBaseHelperElektronik.TABLE_ELECTRONICS, strFilter,
                null);
    }
}
