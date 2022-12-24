package com.example.inventory.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;

public class EditData extends AppCompatActivity implements View.OnClickListener {
    private DataSource dataSource;
    private long id;
    private String nama;
    private String email;
    private String nohp;
    private String alamat;
    private EditText etnama;
    private EditText etemail;
    private EditText etnohp;
    private EditText etalamat;
    private Button btnSimpan;
    private Button btnKembali;
    private Supplier supplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_supplier);
        //inisialisasi variabel
        etnama = (EditText)
                findViewById(R.id.etNama);
        etemail = (EditText)
                findViewById(R.id.etEmail);
        etnohp = (EditText)
                findViewById(R.id.etNohp);
        etalamat = (EditText)
                findViewById(R.id.etAlamat);

        //buat sambungan baru ke database
        dataSource = new DataSource(this);
        dataSource.open();
        // ambil data supplier dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("nama");
        email = bun.getString("email");
        nohp = bun.getString("nohp");
        alamat = bun.getString("alamat");

        //masukkan data-data supplier tersebut ke field editor
        etnama.setText(nama);
        etemail.setText(email);
        etnohp.setText(nohp);
        etalamat.setText(alamat);

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
            // apabila tombol save diklik (update supplier)
            case R.id.simpan:
                supplier = new Supplier();

                supplier.setNama_supplier(etnama.getText().toString());

                supplier.setEmail(etemail.getText().toString());

                supplier.setNohp(etnohp.getText().toString());

                supplier.setAlamat(etalamat.getText().toString());

                supplier.setId(id);
                dataSource.updateSupplier(supplier);
                Intent i = new Intent(this,
                        ViewData.class);
                startActivity(i);
                com.example.inventory.supplier.EditData.this.finish();
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
