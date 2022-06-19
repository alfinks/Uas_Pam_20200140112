package com.example.uas_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SHOEI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoei);
    }
    public void SHOEI(View v) {
        Intent i = new Intent(this, detail_shoei.class);
        startActivity(i);
    }
    public  void order_shoei(View v){
        Intent k = new Intent(this, order_shoei.class);
        startActivity(k);
    }
}