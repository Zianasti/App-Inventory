package com.example.inventory.supplier;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;

public class ViewSingleData extends AppCompatActivity {
    TextView tvNama, tvEmail, tvNohp, tvAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_single_data_supplier);
        tvNama = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNohp = findViewById(R.id.tvNohp);
        tvAlamat = findViewById(R.id.tvAlamat);

        tvNama.setText(getIntent().getExtras().getString("nama"));
        tvEmail.setText(getIntent().getExtras().getString("email"));
        tvNohp.setText(getIntent().getExtras().getString("nohp"));
        tvAlamat.setText(getIntent().getExtras().getString("alamat"));

        Button buttonKembali = (Button) findViewById(R.id.buttonBack);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
