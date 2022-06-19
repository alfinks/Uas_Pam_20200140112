package com.example.uas_pam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class order_agv extends AppCompatActivity {

    private EditText nama,nik,alamat,kodehelm,no_tlp;
    private Button simpan;

    /*FirebaseFirestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_agv);

        nama = findViewById(R.id.txNama);
        nik = findViewById(R.id.txNIK);
        alamat = findViewById(R.id.Alamat);
        kodehelm = findViewById(R.id.Helm);
        no_tlp = findViewById(R.id.tlp);
        simpan = findViewById(R.id.PesanAgv);

        progressDialog = new ProgressDialog(order_agv.this);
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
            no_tlp.setText(intent.getStringExtra("NpTlp"));
        }
    }

    private void simpanData(String Nama, String NIK, String Alamat, String KodeHelm, String No_Telp) {
        Map<String,Object> agv = new HashMap<>();

        agv.put("Nama", Nama);
        agv.put("NIK", NIK);
        agv.put("Alamat", Alamat);
        agv.put("KodeHelm", KodeHelm);
        agv.put("NoTlp", No_Telp);

        progressDialog.show();

        if (id != null) {
            /**
             *  kode untuk edit data firestore dengan mengambil id
             */
            db.collection("Agv").document(id)
                    .set(agv)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(order_agv.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(order_agv.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            /**
             * Code untuk menambahkan data dengan add
             */
            db.collection("Agv")
                    .add(agv)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(order_agv.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(order_agv.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}
