package com.example.uas_pam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AGV extends AppCompatActivity {

    private Button btpesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agv);


    }
    public void AGV(View v) {
        Intent i = new Intent(this, Detail_Agv.class);
        startActivity(i);
    }
    public  void order_agv(View v){
        Intent j = new Intent(this, order_agv.class);
        startActivity(j);
    }

}


