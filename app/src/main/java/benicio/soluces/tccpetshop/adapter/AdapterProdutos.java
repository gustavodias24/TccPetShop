package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ProductModel productModel = produtos.get(position);

        holder.infos.setText(String.format(
                "%s\nR$%.2f %d disponíveis", productModel.getNome(), productModel.getValor(), productModel.getQuanti()
        ));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.generic_info_text);
        }
    }
}
