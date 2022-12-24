package com.example.inventory.supplier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DataSource {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_EMAIL,DBHelper.COLUMN_NOHP,DBHelper.COLUMN_ALAMAT};

    //DBHelper diinstantiasi pada constructor
    public DataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert supplier ke database
    public Supplier createSupplier(String nama, String email, String nohp, String alamat) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_NOHP, nohp);
        values.put(DBHelper.COLUMN_ALAMAT, alamat);


        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_SUPPLIERS, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_SUPPLIERS, allColumns, DBHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek supplier
        Supplier newSupplier = cursorToSupplier(cursor);

        // close cursor
        cursor.close();

        // mengembalikan supplier baru
        return newSupplier;
    }

    private Supplier cursorToSupplier(Cursor cursor){
        Supplier supplier = new Supplier();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3));

        supplier.setId(cursor.getLong(0));
        supplier.setNama_supplier(cursor.getString(1));
        supplier.setEmail(cursor.getString(2));
        supplier.setNohp(cursor.getString(3));
        supplier.setAlamat(cursor.getString(4));


        return supplier;
    }

    public ArrayList<Supplier> getAllSupplier() {
        ArrayList<Supplier> daftarSupplier = new
                ArrayList<Supplier>();
        // select all SQL query
        Cursor cursor =
                database.query(DBHelper.TABLE_SUPPLIERS,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data supplier ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            Supplier supplier = cursorToSupplier(cursor);
            daftarSupplier.add(supplier);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarSupplier;
    }
    //ambil satu barang sesuai id
    public Supplier getSupplier(long id){
        Supplier supplier = new Supplier();

        Cursor cursor = database.query(DBHelper.TABLE_SUPPLIERS, allColumns, "id_supplier="+id, null, null, null, null);

        cursor.moveToFirst();
        supplier = cursorToSupplier(cursor);
        cursor.close();
        return supplier;
    }
    //update barang yang diedit
    public void updateSupplier(Supplier s)
    {
        //ambil id supplier
        String strFilter = "id_supplier=" + s.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAME,
                s.getNama_supplier());
        args.put(DBHelper.COLUMN_EMAIL,
                s.getEmail());
        args.put(DBHelper.COLUMN_NOHP,
                s.getNohp() );
        args.put(DBHelper.COLUMN_ALAMAT,
                s.getAlamat() );
        //update query
        database.update(DBHelper.TABLE_SUPPLIERS, args,
                strFilter, null);
    }
    // delete supplier sesuai ID
    public void deleteSupplier(long id)
    {
        String strFilter = "id_supplier=" + id;
        database.delete(DBHelper.TABLE_SUPPLIERS, strFilter,
                null);
    }
}
