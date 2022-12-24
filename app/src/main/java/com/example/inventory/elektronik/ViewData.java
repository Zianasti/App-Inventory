package com.example.inventory.elektronik;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.inventory.R;
import com.example.inventory.elektronik.DataSourceElektronik;
import com.example.inventory.elektronik.EditData;
import com.example.inventory.elektronik.Elektronik;
import com.example.inventory.elektronik.ViewSingleData;

import java.util.ArrayList;

public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {
    //inisialisasi controller
    private DataSourceElektronik dataSource;
    //inisialisasi arraylist
    private ArrayList<Elektronik> values;
    private Button buttonEdit;
    private Button buttonDelete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_elektronik);
        dataSource = new DataSourceElektronik(this);
        // buka controller
        dataSource.open();
        // ambil semua data elektronik
        values = dataSource.getAllElektronik();
        // masukkan data elektronik ke array adapter
        ArrayAdapter<Elektronik> adapter = new ArrayAdapter<Elektronik>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Elektronik elektronik = (Elektronik) getListAdapter().getItem(position);
                switchToGetData(elektronik.getId());
            }
        });

        Button buttonKembali = (Button) findViewById(R.id.buttonBack);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //apabila ada long click
    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.show();
        final Elektronik e = (Elektronik) getListAdapter().getItem(pos);
        buttonEdit = (Button) dialog.findViewById(R.id.buttEdit);
        buttonDelete = (Button) dialog.findViewById(R.id.buttDelete);
        //apabila tombol edit diklik
        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              // TODO Auto-generated method stub
                                              switchToEdit(e.getId());
                                              dialog.dismiss();
                                          }
                                      }
        );
        //apabila tombol delete di klik
        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // Delete elektronik
                                                dataSource.deleteElektronik(e.getId());
                                                dialog.dismiss();
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        }
        );
        return true;
    }
    //method untuk edit data
    public void switchToEdit(long id) {
        Elektronik e = dataSource.getElektronik(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", e.getId());
        bun.putString("name", e.getNama_elektronik());
        bun.putString("stok", e.getStok());
        bun.putString("harga_beli", e.getHarga_beli());
        bun.putString("harga_jual", e.getHarga_jual());
        bun.putString("description", e.getDescription());

        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Elektronik e = dataSource.getElektronik(id);
        Intent i = new Intent(this, ViewSingleData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", e.getId());
        bun.putString("name", e.getNama_elektronik());
        bun.putString("stok", e.getStok());
        bun.putString("harga_beli", e.getHarga_beli());
        bun.putString("harga_jual", e.getHarga_jual());
        bun.putString("description", e.getDescription());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        com.example.inventory.elektronik.ViewData.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
