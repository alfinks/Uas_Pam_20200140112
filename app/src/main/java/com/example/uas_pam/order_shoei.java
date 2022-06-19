package com.example.uas_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class order_shoei extends AppCompatActivity {

    private EditText nama,nik,alamat,kodehelm,no_tlp;
    private Button simpan;

    /*FirebaseFirestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shoei);

        nama = findViewById(R.id.txNama);
        nik = findViewById(R.id.txNIK);
        alamat = findViewById(R.id.Alamat);
        kodehelm = findViewById(R.id.kodeshoei);
        no_tlp = findViewById(R.id.notlp);
        simpan = findViewById(R.id.ordershoei);

        progressDialog = new ProgressDialog(order_shoei.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        simpan.setOnClickListener(v->{
            if (nama.getText().length() > 0 || nik.getText().length() > 0 || alamat.getText().length() > 0 ||
                    kodehelm.getText().length() > 0 || no_tlp.getText().length() > 0){
                simpanData(nama.getText().toString(),nik.getText().toString(),alamat.getText().toString(),
                        kodehelm.getText().toString(),no_tlp.getText().toString());
            }
            else {
                Toast.makeText(this, "Data Harus di isi Semua", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("id");
            nama.setText(intent.getStringExtra("Nama"));
            nik.setText(intent.getStringExtra("NIK"));
            alamat.setText(intent.getStringExtra("Alamat"));
            kodehelm.setText(intent.getStringExtra("KodeHelm"));
            no_tlp.setText(intent.getStringExtra("NoTlp"));
        }
    }

    private void simpanData(String Nama, String NIK, String Alamat, String KodeHelm, String No_Tlp) {

        Map<String,Object> Shoei = new HashMap<>();

        Shoei.put("Nama", Nama);
        Shoei.put("NIK", NIK);
        Shoei.put("Alamat", Alamat);
        Shoei.put("KodeHelm", KodeHelm);
        Shoei.put("NoTlp", No_Tlp);

        progressDialog.show();

        if (id != null) {
            /**
             *  kode untuk edit data firestore dengan mengambil id
             */
            db.collection("Shoei").document(id)
                    .set(Shoei)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(order_shoei.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(order_shoei.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            /**
             * Code untuk menambahkan data dengan add
             */
            db.collection("Shoei")
                    .add(Shoei)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(order_shoei.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(order_shoei.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }

    }
}