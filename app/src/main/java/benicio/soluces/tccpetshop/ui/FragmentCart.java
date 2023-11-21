package benicio.soluces.tccpetshop.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
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

import benicio.soluces.tccpetshop.FinalizarCompraActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.databinding.FragmentCartBinding;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class FragmentCart extends BaseFragment<FragmentCartBinding> {


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
                    startActivity(new Intent(getActivity(), FinalizarCompraActivity.class));
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();

        produtos.clear();
        produtos.addAll(CarrinhoUtils.returnCarrinho(requireContext()));
        adapter.notifyDataSetChanged();
    }
}
