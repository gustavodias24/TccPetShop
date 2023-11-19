package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.LayoutEditarAgendamentoBinding;
import benicio.soluces.tccpetshop.model.AgendamentoModel;
import benicio.soluces.tccpetshop.model.OrderModel;

public class AdapterOrdes extends RecyclerView.Adapter<AdapterOrdes.MyViewHolder> {
    Dialog d;

    DatabaseReference refPedidos = FirebaseDatabase.getInstance().getReference().child("agendaments_table");
    List<AgendamentoModel> orders;
    Activity c;


    public AdapterOrdes(List<AgendamentoModel> orders, Activity c) {
        this.orders = orders;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_sub_radio_button, parent,false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AgendamentoModel orderModel = orders.get(position);
        holder.ajuda_btn.setOnClickListener( view -> c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(c.getResources().getString(R.string.zapzap)))));
        holder.data.setText(String.format("Data/Hora: %s\nEstabelecimento: %s\nPet: %s", orderModel.getData(), orderModel.getEstabelecimento(), orderModel.getNomePet()));
        holder.excluir_btn.setOnClickListener( view -> {
            refPedidos.child(orderModel.getIdCliente()).child(orderModel.getId()).removeValue().addOnCompleteListener(task -> {
                if ( task.isSuccessful() ){
                    Toast.makeText(c, "Agendamento excluído com sucesso!.", Toast.LENGTH_SHORT).show();
                    orders.remove(orderModel);
                    this.notifyDataSetChanged();
                }
            });
        });

        holder.editar_btn.setOnClickListener( view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(c);
            LayoutEditarAgendamentoBinding bindingEditar = LayoutEditarAgendamentoBinding.inflate(c.getLayoutInflater());

            bindingEditar.edtData.setText(orderModel.getData());
            bindingEditar.edtEstabelecimento.setText(orderModel.getEstabelecimento());
            bindingEditar.edtNomePet.setText(orderModel.getNomePet());

            bindingEditar.agendar.setOnClickListener( view2 -> {
                orderModel.setData(bindingEditar.edtData.getText().toString());
                orderModel.setEstabelecimento(bindingEditar.edtEstabelecimento.getText().toString());
                orderModel.setNomePet(bindingEditar.edtNomePet.getText().toString());
                refPedidos.child(orderModel.getIdCliente()).child(orderModel.getId()).setValue(
                       orderModel
                ).addOnCompleteListener(task -> {
                    Toast.makeText(c, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                    this.notifyDataSetChanged();
                });
            });
            b.setView(bindingEditar.getRoot());
            d = b.create();
            d.show();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView data;
        Button excluir_btn;
        Button editar_btn;
        Button ajuda_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.history_data);
            excluir_btn = itemView.findViewById(R.id.excluir_agendamente_btn);
            editar_btn = itemView.findViewById(R.id.editar_agendamento);
            ajuda_btn = itemView.findViewById(R.id.ajudar_btn);
        }
    }
}
