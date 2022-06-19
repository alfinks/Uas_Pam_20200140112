package com.example.uas_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.uas_pam.alat.HelmAdapter;
import com.example.uas_pam.database.HelmModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Detail_Agv extends AppCompatActivity {

    RecyclerView recyclerView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<HelmModel> list = new ArrayList<>();
    private HelmAdapter helmAdapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agv);

        recyclerView = findViewById(R.id.dtlagv);

        /*Pembuatan PopUp loading*/
        progressDialog = new ProgressDialog(Detail_Agv.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        helmAdapter = new HelmAdapter(getApplicationContext(), list);

        helmAdapter.setDialog(new HelmAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit Data", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detail_Agv.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i) {
                            /*Update Data*/
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), order_agv.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("Nama", list.get(pos).getNama());
                                intent.putExtra("NIK", list.get(pos).getNik());
                                intent.putExtra("Alamat", list.get(pos).getAlamat());
                                intent.putExtra("KodeHelm", list.get(pos).getKode_helm());
                                intent.putExtra("NoTlp", list.get(pos).getNo_tlp());
                                startActivity(intent);
                                break;
                            /*Hapus Data*/
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(helmAdapter);

    }

    private void deleteData(String id) {
        progressDialog.show();

        db.collection("Agv").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(Detail_Agv.this, "Data Gagal Di hapus", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Detail_Agv.this, "Data Berhasil Di hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }


    private void getData() {
        progressDialog.show();

        db.collection("Agv")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()){
                                HelmModel helmModel = new HelmModel(document.getString("Nama"), document.getString("NIK"), document.getString("Alamat"), document.getString("KodeHelm"),
                                        document.getString("NoTlp"));
                                helmModel.setId(document.getId());
                                list.add(helmModel);
                            }
                            helmAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(Detail_Agv.this, "Data Gagal di Ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
