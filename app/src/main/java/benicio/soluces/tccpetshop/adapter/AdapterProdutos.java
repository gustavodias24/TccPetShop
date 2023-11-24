package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.LayoutEditarQtdProdutoBinding;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.myViewHolder>{

    List<ProductModel> produtos;
    Context c;
    Activity a;
    Boolean clicavel, carrinho;

    public AdapterProdutos(List<ProductModel> produtos, Context c, Boolean clicavel, Boolean carrinho, Activity a) {
        this.produtos = produtos;
        this.c = c;
        this.clicavel = clicavel;
        this.carrinho = carrinho;
        this.a = a;
    }

    public AdapterProdutos(List<ProductModel> produtos, Context c, Boolean clicavel, Boolean carrinho) {
        this.produtos = produtos;
        this.c = c;
        this.clicavel = clicavel;
        this.carrinho = carrinho;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_info_layout, parent,false));
    }

    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ProductModel productModel = produtos.get(position);

        holder.imageProduto.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(productModel.getImagem())).into(holder.imageProduto);
        holder.infos.setText(String.format(
                "%s\n%d disponíveis\nR$%.2f", productModel.getNome(), productModel.getQuanti(),productModel.getValor()
        ));

        if ( clicavel ){
            holder.itemView.getRootView().setOnClickListener( view -> {
                Log.d("aquiaqui", "onBindViewHolder: " + "adicicado");
                Toast.makeText(c, "Adicionado ao carrinho", Toast.LENGTH_LONG).show();
                List<ProductModel> listaAntiga = CarrinhoUtils.returnCarrinho(c);
                listaAntiga.add(productModel);
                CarrinhoUtils.saveCarrinho(listaAntiga, c);
            });
        }

        if ( carrinho ){
            holder.quantidade_produto.setVisibility(View.VISIBLE);
            holder.quantidade_produto.setText(String.format("x%d", productModel.getQuantiadeComprada()));

            holder.quantidade_produto.setOnClickListener( view -> {
                AlertDialog.Builder b = new AlertDialog.Builder(c);
                b.setTitle("Editar quantidade.");
                LayoutEditarQtdProdutoBinding leqp = LayoutEditarQtdProdutoBinding.inflate(a.getLayoutInflater());
                leqp.edtQtdProd.setText(productModel.getQuantiadeComprada() + "");

                leqp.editar.setOnClickListener( view1 -> { productModel.setQuantiadeComprada(
                        Integer.parseInt(leqp.edtQtdProd.getText().toString())
                );

                List<ProductModel> listaAntiga = CarrinhoUtils.returnCarrinho(c);
                listaAntiga.remove(position);
                CarrinhoUtils.saveCarrinho(listaAntiga, c);
                Toast.makeText(c, "Editado com suceso", Toast.LENGTH_SHORT).show();

                this.notifyDataSetChanged();
                });

                b.setView(leqp.getRoot()).create().show();
            });
        }

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class  myViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        TextView quantidade_produto;
        ImageView imageProduto;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.generic_info_text);
            imageProduto = itemView.findViewById(R.id.imageProduto);
            quantidade_produto = itemView.findViewById(R.id.text_quantidade_pedido);
        }
    }
}
