package benicio.soluces.tccpetshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.tccpetshop.FinalizarCompraActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.model.PedidoCompraModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class AdapterHistoricoCompras extends RecyclerView.Adapter<AdapterHistoricoCompras.MyViewHolder>{

    List<PedidoCompraModel> pedidosCompras;
    Context c;

    public AdapterHistoricoCompras(List<PedidoCompraModel> pedidosCompras, Context c) {
        this.pedidosCompras = pedidosCompras;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(c).inflate(R.layout.item_produto_comprarnovamente, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PedidoCompraModel pedidoCompraModel = pedidosCompras.get(position);

        holder.textProduto.setText(String.format("( %d ) Itens da compra:", position + 1));
        RecyclerView r = holder.produtosCompradosPedido;
        r.setLayoutManager(new LinearLayoutManager(c));
        r.addItemDecoration(new DividerItemDecoration(c, DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);

        r.setAdapter(new AdapterProdutos(pedidoCompraModel.getProdutosComprados(), c, false, false));


        holder.btnComprarNovamente.setOnClickListener( view -> {
            CarrinhoUtils.saveCarrinho(pedidoCompraModel.getProdutosComprados(), c);
            Intent i = new Intent(c, FinalizarCompraActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return pedidosCompras.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        RecyclerView produtosCompradosPedido;
        Button btnComprarNovamente;
        TextView textProduto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            produtosCompradosPedido = itemView.findViewById(R.id.recycler_compras_produtos);
            btnComprarNovamente = itemView.findViewById(R.id.btn_comprar_novamente);
            textProduto = itemView.findViewById(R.id.infos_item_produto);
        }
    }
}
