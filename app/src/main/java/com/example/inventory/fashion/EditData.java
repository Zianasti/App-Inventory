package com.example.inventory.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import com.example.inventory.R;


public class EditData extends AppCompatActivity implements View.OnClickListener {
    private DataSourceFashion dataSource;
    private long id;
    private String nama;
    private String stok;
    private String harga_beli;
    private String harga_jual;
    private String desc;
    private EditText etnama;
    private EditText etstok;
    private EditText etharga_beli;
    private EditText etharga_jual;
    private EditText etDesc;
    private Button btnSimpan;
    private Button btnKembali;
    private Fashion fashion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_fashion);
        //inisialisasi variabel
        etnama = (EditText)
                findViewById(R.id.etNama);
        etstok = (EditText)
                findViewById(R.id.etStok);
        etharga_beli = (EditText)
                findViewById(R.id.etHargaBeli);
        etharga_jual = (EditText)
                findViewById(R.id.etHargaJual);
        etDesc = (EditText)
                findViewById(R.id.etDesc);
        //buat sambungan baru ke database
        dataSource = new DataSourceFashion(this);
        dataSource.open();
        // ambil data fashion dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("name");
        stok = bun.getString("stok");
        harga_beli = bun.getString("harga_beli");
        harga_jual = bun.getString("harga_jual");
        desc = bun.getString("description");

        //masukkan data-data fashion tersebut ke field editor
        etnama.setText(nama);
        etstok.setText(stok);
        etharga_beli.setText(harga_beli);
        etharga_jual.setText(harga_jual);
        etDesc.setText(desc);

        //set listener pada tombol
        btnSimpan = (Button)
                findViewById(R.id.simpan);
        btnSimpan.setOnClickListener(this);
        btnKembali = (Button)
                findViewById(R.id.batal);
        btnKembali.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update fashion)
            case R.id.simpan:
                fashion = new Fashion();

                fashion.setNama_fashion(etnama.getText().toString());

                fashion.setStok(etstok.getText().toString());

                fashion.setHarga_beli(etharga_beli.getText().toString());

                fashion.setHarga_jual(etharga_jual.getText().toString());

                fashion.setDescription(etDesc.getText().toString());

                fashion.setId(id);
                dataSource.updateFashion(fashion);
                Intent i = new Intent(this,
                        ViewData.class);
                startActivity(i);
                EditData.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.batal :
                finish();
                dataSource.close();
                break;
        }
    }
}
