package com.example.inventory.elektronik;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;


public class CreateData extends AppCompatActivity implements View.OnClickListener {
    //inisilisasi elemen-elemen pada layout
    private Button button;
    private EditText etNama;
    private EditText etStok;
    private EditText etHarga_beli;
    private EditText etHarga_jual;
    private EditText etDesc;


    //inisialisasi kontroller/Data Source
    private DataSourceElektronik dataSource;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_elektronik);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        etNama = (EditText) findViewById(R.id.etName);
        etStok = (EditText) findViewById(R.id.etStok);
        etHarga_beli = (EditText) findViewById(R.id.etHargaBeli);
        etHarga_jual = (EditText) findViewById(R.id.etHargaJual);
        etDesc = (EditText) findViewById(R.id.etDesc);


        // instanstiasi kelas DataSourceElektronik
        dataSource = new DataSourceElektronik(this);

        //membuat sambungan baru ke database
        dataSource.open();

        Button buttonKembali = (Button) findViewById(R.id.buttonBack);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //KETIKA Tombol Submit Diklik
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        // Inisialisasi data elektronik
        String nama = null;
        String stok = null;
        String harga_beli = null;
        String harga_jual = null;
        String description = null;

        @SuppressWarnings("unused")
        //inisialisasi elektronik baru (masih kosong)
        Elektronik elektronik = null;
        if(etNama.getText()!=null &&
                etStok.getText()!=null && etHarga_beli.getText()!=null && etHarga_jual.getText()!=null && etDesc.getText()!=null)
        {
            /* jika field nama, stok, harga_beli, harga_jual, dan deskripsi tidak kosong
             * maka masukkan ke dalam data elektronik*/
            nama = etNama.getText().toString();
            stok = etStok.getText().toString();
            harga_beli = etHarga_beli.getText().toString();
            harga_jual = etHarga_jual.getText().toString();
            description = etDesc.getText().toString();

        }
        switch(v.getId())
        {
            case R.id.button:
                // insert data barang baru
                elektronik = dataSource.createElektronik(nama, stok, harga_beli,
                        harga_jual, description);
                //konfirmasi kesuksesan
                Toast.makeText(this, "Barang " +
                                elektronik.getNama_elektronik() +
                                " Berhasil Disimpan !",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
