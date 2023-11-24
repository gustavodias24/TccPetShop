package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import benicio.soluces.tccpetshop.ExibirLojaActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.model.StoreModel;

public class AdapterStores extends RecyclerView.Adapter<AdapterStores.MyViewHolder>{

    List<StoreModel> stores;
    Context context;


    public AdapterStores(List<StoreModel> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.exibicao_info_layout, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreModel store = stores.get(position);

        holder.infos.setText(
                String.format(
                        "%s\nRaio de distância: %dm\nAgenda:\n%s", store.getNomeLoja(), store.getRaio(), store.getAgenda()
                )
        );

        Picasso.get().load(Uri.parse(store.getImage())).into(holder.imageStore);

        holder.itemView.getRootView().setOnClickListener(view -> {
            Intent i = new Intent(context, ExibirLojaActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", store.getId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView infos;
        ImageView imageStore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            infos = itemView.findViewById(R.id.generic_info_text);
            imageStore = itemView.findViewById(R.id.imageProduto);
        }
    }
}
