package com.example.uas_pam.alat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pam.R;
import com.example.uas_pam.database.HelmModel;

import java.util.List;

public class HelmAdapter extends RecyclerView.Adapter<HelmAdapter.HelmViewHolder> {

    private Context context;
    private List<HelmModel> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public HelmAdapter(Context context, List<HelmModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HelmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new HelmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HelmViewHolder holder, int position) {
        holder.namaPelanggan.setText("Nama : "+list.get(position).getNama());
        holder.NIK.setText("NIK : "+list.get(position).getNik());
        holder.Alamat.setText("Alamat : "+list.get(position).getAlamat());
        holder.kodeHelm.setText("Kode Helm : "+list.get(position).getKode_helm());
        holder.NoTlp.setText("No Telp : "+list.get(position).getNo_tlp());

        holder.namaPelanggan.setTextColor(Color.BLACK);
        holder.kodeHelm.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HelmViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView namaPelanggan, NIK, Alamat, kodeHelm, NoTlp;

        public HelmViewHolder(@NonNull  View itemView){
            super(itemView);

            cardView = itemView.findViewById(R.id.cardMotor);
            namaPelanggan = itemView.findViewById(R.id.NamaPelanggan);
            NIK = itemView.findViewById(R.id.NIK);
            Alamat = itemView.findViewById(R.id.tcAlamat);
            kodeHelm = itemView.findViewById(R.id.kodeHelm);
            NoTlp = itemView.findViewById(R.id.notlp);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
