package com.example.inventory.supplier;

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
    private Button buttonSubmit;
    private EditText etNama;
    private EditText etEmail;
    private EditText etNohp;
    private EditText etAlamat;

    //inisialisasi kontroller/Data Source
    private DataSource dataSource;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_supplier);
        buttonSubmit = (Button) findViewById(R.id.button);
        buttonSubmit.setOnClickListener(this);
        etNama = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNohp = (EditText) findViewById(R.id.etNohp);
        etAlamat = (EditText) findViewById(R.id.etAlamat);

        // instanstiasi kelas DataSource
        dataSource = new DataSource(this);

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
        // Inisialisasi data barang
        String nama = null;
        String email = null;
        String nohp = null;
        String alamat = null;

        @SuppressWarnings("unused")
        //inisialisasi supplier baru (masih kosong)
        Supplier supplier = null;
        if(etNama.getText()!=null &&
                etEmail.getText()!=null && etNohp.getText()!=null && etAlamat.getText()!=null)
        {
            /* jika field nama, email, nohp, dan alamat tidak kosong
             * maka masukkan ke dalam data supplier*/
            nama = etNama.getText().toString();
            email = etEmail.getText().toString();
            nohp = etNohp.getText().toString();
            alamat = etAlamat.getText().toString();

        }
        switch(v.getId())
        {
            case R.id.button:
                // insert data supplier baru
                supplier = dataSource.createSupplier(nama,
                        email, nohp, alamat);
                //konfirmasi kesuksesan
                Toast.makeText(this, "Supplier " +
                          supplier.getNama_supplier() +" Berhasil ditambahkan !"
                        , Toast.LENGTH_LONG).show();
                break;
        }
    }
}
