package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.model.ProductModel;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.myViewHolder>{

    List<ProductModel> produtos;

    public AdapterProdutos(List<ProductModel> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_info_layout, parent,false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ProductModel productModel = produtos.get(position);

        holder.imageProduto.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(productModel.getImagem())).into(holder.imageProduto);
        holder.infos.setText(String.format(
                "%s\n%d disponíveis\nR$%.2f", productModel.getNome(), productModel.getQuanti(),productModel.getValor()
        ));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class  myViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        ImageView imageProduto;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.generic_info_text);
            imageProduto = itemView.findViewById(R.id.imageProduto);
        }
    }
}
