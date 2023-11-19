package benicio.soluces.tccpetshop.ui;


import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            });
    }

    private void configurarRecyclerView(){
        r = getBinding().recylerCarrinho;
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        r.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        produtos.addAll(CarrinhoUtils.returnCarrinho(requireContext()));
        adapter = new AdapterProdutos(produtos, getContext(), true);
        r.setAdapter(adapter);
    }
}
