package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.adapter.AdapterStores;
import benicio.soluces.tccpetshop.databinding.FragmentExplorerBinding;
import benicio.soluces.tccpetshop.model.ProductModel;


public class FragmentExplorer extends BaseFragment<FragmentExplorerBinding> {

    DatabaseReference refProduct = FirebaseDatabase.getInstance().getReference().child("product_table");
    List<ProductModel> produtos = new ArrayList<>();
    AdapterProdutos adapter;
    RecyclerView r;
    public FragmentExplorer() { super(R.layout.fragment_explorer, FragmentExplorerBinding::bind);}

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindCreated(FragmentExplorerBinding binding) {

        configurarRecyclerView();
        binding.imgAdvertising.setImageDrawable(getResources().getDrawable(R.drawable.propaganda));

        binding.btnSearch.edtPesquisaProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filtrarDados(charSequence.toString());

                if ( !charSequence.toString().isEmpty() ){
                    binding.recyclerProdutos.setVisibility(View.VISIBLE);
                    binding.layoutPopular.setVisibility(View.GONE);
                }else{
                    binding.recyclerProdutos.setVisibility(View.GONE);
                    binding.layoutPopular.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.seeAllPopularProduct.setOnClickListener(view -> {
            refProduct.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        produtos.clear();

                        for ( DataSnapshot dado : snapshot.getChildren() ){
                            produtos.add(dado.getValue(ProductModel.class));
                        }
                        adapter.notifyDataSetChanged();
                        binding.layoutPopular.setVisibility(View.GONE);
                        binding.recyclerProdutos.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
    private void configurarRecyclerView(){
        r = getBinding().recyclerProdutos;
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        r.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapter = new AdapterProdutos(produtos, getContext(), true);
        r.setAdapter(adapter);
    }
    private void filtrarDados(String texto) {

        Query query = refProduct.orderByChild("nome").startAt(texto).endAt(texto + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                produtos.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProductModel productModel = snapshot.getValue(ProductModel.class);
                    produtos.add(productModel);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
