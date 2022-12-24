package com.example.inventory.supplier;

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


import java.util.ArrayList;

public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {
    //inisialisasi kontroller
    private DataSource dataSource;
    //inisialisasi arraylist
    private ArrayList<Supplier> values;
    private Button buttonEdit;
    private Button buttonDelete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_supplier);
        dataSource = new DataSource(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data supplier
        values = dataSource.getAllSupplier();
        // masukkan data supplier ke array adapter
        ArrayAdapter<Supplier> adapter = new ArrayAdapter<Supplier>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Supplier supplier = (Supplier) getListAdapter().getItem(position);
                switchToGetData(supplier.getId());
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
        final Supplier s = (Supplier) getListAdapter().getItem(pos);
        buttonEdit = (Button) dialog.findViewById(R.id.buttEdit);
        buttonDelete = (Button) dialog.findViewById(R.id.buttDelete);
        //apabila tombol edit diklik
        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              // TODO Auto-generated method stub
                                              switchToEdit(s.getId());
                                              dialog.dismiss();
                                          }
                                      }
        );
        //apabila tombol delete di klik
        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // Delete fashion
                                                dataSource.deleteSupplier(s.getId());
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
        Supplier s = dataSource.getSupplier(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", s.getId());
        bun.putString("nama", s.getNama_supplier());
        bun.putString("email", s.getEmail());
        bun.putString("nohp", s.getNohp());
        bun.putString("alamat", s.getAlamat());

        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Supplier s = dataSource.getSupplier(id);
        Intent i = new Intent(this, ViewSingleData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", s.getId());
        bun.putString("nama", s.getNama_supplier());
        bun.putString("email", s.getEmail());
        bun.putString("nohp", s.getNohp());
        bun.putString("alamat", s.getAlamat());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        com.example.inventory.supplier.ViewData.this.finish();
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
