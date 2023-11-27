package benicio.soluces.tccpetshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.model.CartaoModel;

public class AdapterCc extends RecyclerView.Adapter<AdapterCc.MyViewHolder>{

    List<CartaoModel> lista;
    Context c;

    public AdapterCc(List<CartaoModel> lista, Context c) {
        this.lista = lista;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCc.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_info_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartaoModel cc = lista.get(position);

        holder.info.setText(cc.toString());
        holder.img.setVisibility(View.GONE);
        holder.itemView.getRootView().setOnClickListener( view -> Toast.makeText(c,
                String.format("Cartão %s selecionado", cc.getNomeCartao()), Toast.LENGTH_SHORT));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView info; ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            info = itemView.findViewById(R.id.generic_info_text);
            img = itemView.findViewById(R.id.imageProduto);
        }
    }
}
