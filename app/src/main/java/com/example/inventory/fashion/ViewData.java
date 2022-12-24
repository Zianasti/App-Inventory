package com.example.inventory.fashion;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import com.example.inventory.R;
import com.example.inventory.fashion.DataSourceFashion;
import com.example.inventory.fashion.Fashion;

public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {
    //inisialisasi kontroller
    private DataSourceFashion dataSource;
    //inisialisasi arraylist
    private ArrayList<Fashion> values;
    private Button buttonEdit;
    private Button buttonDelete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_fashion);
        dataSource = new DataSourceFashion(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data fashion
        values = dataSource.getAllFashion();
        // masukkan data fashion ke array adapter
        ArrayAdapter<Fashion> adapter = new ArrayAdapter<Fashion>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fashion fashion = (Fashion) getListAdapter().getItem(position);
                switchToGetData(fashion.getId());
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
        final Fashion b = (Fashion) getListAdapter().getItem(pos);
        buttonEdit = (Button) dialog.findViewById(R.id.buttEdit);
        buttonDelete = (Button) dialog.findViewById(R.id.buttDelete);
        //apabila tombol edit diklik
        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              // TODO Auto-generated method stub
                                              switchToEdit(b.getId());
                                              dialog.dismiss();
                                          }
                                      }
        );
        //apabila tombol delete di klik
        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             // Delete fashion
                                             dataSource.deleteFashion(b.getId());
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
        Fashion f = dataSource.getFashion(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", f.getId());
        bun.putString("name", f.getNama_fashion());
        bun.putString("stok", f.getStok());
        bun.putString("harga_beli", f.getHarga_beli());
        bun.putString("harga_jual", f.getHarga_jual());
        bun.putString("description", f.getDescription());

        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Fashion f = dataSource.getFashion(id);
        Intent i = new Intent(this, ViewSingleData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", f.getId());
        bun.putString("name", f.getNama_fashion());
        bun.putString("stok", f.getStok());
        bun.putString("harga_beli", f.getHarga_beli());
        bun.putString("harga_jual", f.getHarga_jual());
        bun.putString("description", f.getDescription());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        ViewData.this.finish();
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
