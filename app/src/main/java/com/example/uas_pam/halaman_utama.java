package com.example.uas_pam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class halaman_utama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);
    }
    public void AGV(View v) {
        Intent i = new Intent(this, AGV.class);
        startActivity(i);
    }

    public void Shoei(View v) {
        Intent i = new Intent(this, SHOEI.class);
        startActivity(i);
    }
}
