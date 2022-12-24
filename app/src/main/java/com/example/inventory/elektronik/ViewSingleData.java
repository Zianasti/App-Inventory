package com.example.inventory.elektronik;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;

public class ViewSingleData extends AppCompatActivity {
    TextView tvNama, tvStok, tvHarga_beli, tvHarga_jual, tvDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_single_data_elektronik);
        tvNama = findViewById(R.id.tvName);
        tvStok = findViewById(R.id.tvStok);
        tvHarga_beli = findViewById(R.id.tvHargaBeli);
        tvHarga_jual = findViewById(R.id.tvHargaJual);
        tvDesc = findViewById(R.id.tvDeskripsi);

        tvNama.setText(getIntent().getExtras().getString("name"));
        tvStok.setText(getIntent().getExtras().getString("stok"));
        tvHarga_beli.setText(getIntent().getExtras().getString("harga_beli"));
        tvHarga_jual.setText(getIntent().getExtras().getString("harga_jual"));
        tvDesc.setText(getIntent().getExtras().getString("description"));

        Button buttonKembali = (Button) findViewById(R.id.buttonBack);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
