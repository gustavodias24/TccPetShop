package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.databinding.ActivityFinalizarCompraBinding;
import benicio.soluces.tccpetshop.model.PedidoCompraModel;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class FinalizarCompraActivity extends AppCompatActivity {

    SharedPreferences preferences;
    DatabaseReference refProduct = FirebaseDatabase.getInstance().getReference().child("product_table");
    DatabaseReference orderProduct = FirebaseDatabase.getInstance().getReference().child("order_product_table");
    private ActivityFinalizarCompraBinding binding;
    private RecyclerView r;
    List<ProductModel> produtos = new ArrayList<>();
    AdapterProdutos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalizarCompraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configurarRecyclerView();
        binding.voltar.setOnClickListener(view -> {finish();});

        binding.btnCompraConcluida.setOnClickListener( view -> {
            criarRegistroPedido();
            Toast.makeText(this, "Carregando... aguarde!", Toast.LENGTH_SHORT).show();
            atualizarEstoqueProdutos();
        });

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);
    }

    private void criarRegistroPedido(){
        String idPedido = UUID.randomUUID().toString();
        orderProduct.child(preferences.getString("idUsuario", "")).child(idPedido).setValue(
                new PedidoCompraModel(
                        CarrinhoUtils.returnCarrinho(getApplicationContext()),
                        idPedido,
                        preferences.getString("idUsuario", "")
                )
        );
    }
    private void atualizarEstoqueProdutos(){

        refProduct.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists() ){
                    for ( DataSnapshot dado : snapshot.getChildren()){
                        ProductModel productModel = dado.getValue(ProductModel.class);

                        for ( ProductModel produtoCart : produtos){
                            if ( produtoCart.getId() == productModel.getId()){
                                int quantiVedida = productModel.getquantiVenda();
                                quantiVedida++;
                                int quantidadeEstoque = productModel.getQuanti();
                                if ( quantidadeEstoque > 0){
                                    quantidadeEstoque--;
                                }else{
                                    Toast.makeText(FinalizarCompraActivity.this, "Não foi possível comprar " + produtoCart.getNome() + " por falta de estoque.", Toast.LENGTH_SHORT).show();
                                }

                                productModel.setquantiVenda(
                                        quantiVedida
                                );

                                productModel.setQuanti(
                                        quantidadeEstoque
                                );

                                refProduct.child(productModel.getId() + "").setValue(productModel);
                            }
                        }
                    }

                    CarrinhoUtils.saveCarrinho(new ArrayList<>(), getApplicationContext());
                    produtos.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Compra realizada com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void configurarRecyclerView(){
        r = binding.recylerCarrinho;
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        r.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        produtos.addAll(CarrinhoUtils.returnCarrinho(getApplicationContext()));
        adapter = new AdapterProdutos(produtos, getApplicationContext(), true);
        r.setAdapter(adapter);
    }


}