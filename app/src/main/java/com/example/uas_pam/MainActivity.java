package com.example.uas_pam;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
    //untuk button
    Button Signin;

    //untuk TextView
    TextView edRegister;

    //untuk editText
    EditText edNama, edPassword;

    //untuk menyimpan nama dan password
    String nama,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNama = findViewById(R.id.mname);
        edPassword = findViewById(R.id.mpass);
        edRegister = findViewById(R.id.regis);
        Signin = findViewById(R.id.sigin);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = edNama.getText().toString();
                password = edPassword.getText().toString();

                Bundle b = new Bundle();

                b.putString("name", nama.trim());

                if (TextUtils.isEmpty(nama) && TextUtils.isEmpty(password)){
                    edNama.setError("Nama harus diisi");

                    Toast t = Toast.makeText(getApplicationContext(), "Masukkan Nama dan Password Anda", Toast.LENGTH_LONG);
                    t.show();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast t = Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT);
                    t.show();
                    edPassword.setError("Password harus diisi");

                }
                else if (TextUtils.isEmpty(nama)){
                    edNama.setError("Nama harus isi");
                }
                else {
                    if (nama.equals("alfin") && password.equals("123")){
                        Toast t = Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_SHORT);
                        t.show();

                        Intent log = new Intent(getApplicationContext(), halaman_utama.class);
                        log.putExtras(b);
                        startActivity(log);
                    }
                    else if (nama.equals("alfin") && !password.equals("123")){
                        Toast t = Toast.makeText(getApplicationContext(), "Password Anda Salah", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else if(!nama.equals("alfin") && password.equals("123")){
                        Toast t = Toast.makeText(getApplicationContext(), "Nama Anda Salah", Toast.LENGTH_LONG);
                        t.show();
                    }
                    else {
                        Toast t = Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
            }
        });

        edRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_2.class);
                startActivity(i);
            }
        });
    }
}