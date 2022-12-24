package com.example.inventory.fashion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inventory.DatabaseHelper;
import com.example.inventory.fashion.Fashion;

import java.util.ArrayList;

public class DataSourceFashion {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DataBaseHelperFashion
    private DatabaseHelperFashion dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DatabaseHelperFashion.COL_ID_FASHION,
            DatabaseHelperFashion.COL_NAME_FASHION,
            DatabaseHelperFashion.COL_STOCKF,DatabaseHelperFashion.COL_HARGA_BELIF,DatabaseHelperFashion.COL_HARGA_JUALF,DatabaseHelperFashion.COL_DESCRIPTIONF};

    //DBHelper diinstantiasi pada constructor
    public DataSourceFashion(Context context)
    {
        dbHelper = new DatabaseHelperFashion(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert fashion ke database
    public Fashion createFashion(String name, String stok , String harga_beli, String harga_jual, String description) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperFashion.COL_NAME_FASHION, name);
        values.put(DatabaseHelperFashion.COL_STOCKF, stok);
        values.put(DatabaseHelperFashion.COL_HARGA_BELIF, harga_beli);
        values.put(DatabaseHelperFashion.COL_HARGA_JUALF, harga_jual);
        values.put(DatabaseHelperFashion.COL_DESCRIPTIONF, description);

        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DatabaseHelperFashion.TABLE_FASHIONS, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DatabaseHelperFashion.TABLE_FASHIONS, allColumns, DatabaseHelperFashion.COL_ID_FASHION + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek fashion
        Fashion newFashion = cursorToFashion(cursor);

        // close cursor
        cursor.close();

        // mengembalikan fashion baru
        return newFashion;
    }
    private Fashion cursorToFashion(Cursor cursor){
        Fashion fashion = new Fashion();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4));

        fashion.setId(cursor.getLong(0));
        fashion.setNama_fashion(cursor.getString(1));
        fashion.setStok(cursor.getString(2));
        fashion.setHarga_beli(cursor.getString(3));
        fashion.setHarga_jual(cursor.getString(4));
        fashion.setDescription(cursor.getString(5));

        return fashion;
    }

    public ArrayList<Fashion> getAllFashion() {
        ArrayList<Fashion> daftarFashion = new
                ArrayList<Fashion>();
        // select all SQL query
        Cursor cursor =
                database.query(DatabaseHelperFashion.TABLE_FASHIONS,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data fashion ke
        // daftar fashion
        while (!cursor.isAfterLast()) {
            Fashion fashion = cursorToFashion(cursor);
            daftarFashion.add(fashion);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarFashion;
    }
    //ambil satu fashion sesuai id
    public Fashion getFashion(long id){
        Fashion fashion = new Fashion();

        Cursor cursor = database.query(DatabaseHelperFashion.TABLE_FASHIONS, allColumns, "id_fashion="+id, null, null, null, null);

        cursor.moveToFirst();
        fashion = cursorToFashion(cursor);
        cursor.close();
        return fashion;
    }
    //update fashion yang diedit
    public void updateFashion(Fashion f)
    {
        //ambil id fashion
        String strFilter = "id_fashion=" + f.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DatabaseHelperFashion.COL_NAME_FASHION,
                f.getNama_fashion());
        args.put(DatabaseHelperFashion.COL_STOCKF,
                f.getStok());
        args.put(DatabaseHelperFashion.COL_HARGA_BELIF,
                f.getHarga_beli());
        args.put(DatabaseHelperFashion.COL_HARGA_JUALF,
                f.getHarga_jual());
        args.put(DatabaseHelperFashion.COL_DESCRIPTIONF,
                f.getDescription());
        //update query
        database.update(DatabaseHelperFashion.TABLE_FASHIONS, args,
                strFilter, null);
    }
    // delete fashion sesuai ID
    public void deleteFashion(long id)
    {
        String strFilter = "id_fashion=" + id;
        database.delete(DatabaseHelperFashion.TABLE_FASHIONS, strFilter,
                null);
    }
}
