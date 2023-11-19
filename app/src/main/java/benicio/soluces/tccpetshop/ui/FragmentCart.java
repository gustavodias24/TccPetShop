package benicio.soluces.tccpetshop.ui;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.databinding.FragmentCartBinding;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class FragmentCart extends BaseFragment<FragmentCartBinding> {

    DatabaseReference refProduct = FirebaseDatabase.getInstance().getReference().child("product_table");
    public FragmentCart() { super(R.layout.fragment_cart, FragmentCartBinding::bind);}

    List<ProductModel> produtos = new ArrayList<>();
    AdapterProdutos adapter;
    RecyclerView r;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindCreated(FragmentCartBinding binding) {
            configurarRecyclerView();

            binding.limparCarrinho.setOnClickListener(view -> {
                Toast.makeText(getContext(), "Carrinho limpo", Toast.LENGTH_SHORT).show();
                CarrinhoUtils.saveCarrinho(new ArrayList<>(), requireContext());
                produtos.clear();
                adapter.notifyDataSetChanged();
                binding.limparCarrinho.setVisibility(View.GONE);
            });

            binding.btnComprar.setOnClickListener( view -> {
                if ( produtos.isEmpty() ){
                    Toast.makeText(getContext(), "Nenhum produto selecionado!", Toast.LENGTH_SHORT).show();
                }else{
                    atualizarEstoqueProdutos();
                    Toast.makeText(getContext(), "Carregando...", Toast.LENGTH_SHORT).show();
                }
            });
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

                    CarrinhoUtils.saveCarrinho(new ArrayList<>(), requireContext());
                    produtos.clear();
                    adapter.notifyDataSetChanged();
                    getBinding().limparCarrinho.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void configurarRecyclerView(){
        r = getBinding().recylerCarrinho;
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        r.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        produtos.addAll(CarrinhoUtils.returnCarrinho(requireContext()));
        if ( produtos.isEmpty()) { getBinding().limparCarrinho.setVisibility(View.GONE);}
        adapter = new AdapterProdutos(produtos, getContext(), true);
        r.setAdapter(adapter);
    }
}
