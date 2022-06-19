package com.example.uas_pam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_2 extends AppCompatActivity {

    Button btRegis;

    EditText edNama, edEmail, edPass, edRepas;
    String nama, email, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btRegis = findViewById(R.id.button);
        edNama = findViewById(R.id.txNama);
        edEmail = findViewById(R.id.txEmail);
        edPass = findViewById(R.id.Pass);
        edRepas = findViewById(R.id.reepass);

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = edNama.getText().toString();
                email = edEmail.getText().toString();
                password = edPass.getText().toString();
                repassword = edRepas.getText().toString();

                if (TextUtils.isEmpty(nama) && TextUtils.isEmpty(email)
                        && TextUtils.isEmpty(password) && TextUtils.isEmpty(repassword)){
                    Toast t = Toast.makeText(getApplicationContext(),"Harap isi semua kolom diatas",Toast.LENGTH_LONG);
                    t.show();

                    edNama.setError("Nama harus diisi");
                    edEmail.setError("Email harus diisi");
                }
                else if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)){
                    Toast t = Toast.makeText(getApplicationContext(),"Harap isi semua data!",Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    if (!password.equals(repassword)){
                        Toast t = Toast.makeText(getApplicationContext(),"Password harus sama!",Toast.LENGTH_LONG);
                        t.show();

                        edRepas.setError("Password tidak cocok");
                    }
                    else {
                        Bundle b = new Bundle();

                        b.putString("name", nama.trim());
                        Toast t = Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil", Toast.LENGTH_LONG);
                        t.show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            }
        });
    }
}

