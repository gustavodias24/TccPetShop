package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.soluces.tccpetshop.ExibirProdutoActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.adapter.AdapterStores;
import benicio.soluces.tccpetshop.databinding.FragmentExplorerBinding;
import benicio.soluces.tccpetshop.model.ProductModel;


public class FragmentExplorer extends BaseFragment<FragmentExplorerBinding> implements View.OnClickListener {

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

        Picasso.get().load(Uri.parse("https://i.pinimg.com/originals/3d/df/a1/3ddfa1367edf3451cb7d71836e5332c3.jpg")).into(binding.btnPopularProd2);
        Picasso.get().load(Uri.parse("https://i.pinimg.com/originals/03/d8/69/03d86927b2b0cd88ff09b3519bfd0309.jpg")).into(binding.btnPopularProd3);
        Picasso.get().load(Uri.parse("https://tuchogiusti.files.wordpress.com/2007/04/soluti-on.jpg")).into(binding.btnPopularProd4);
        Picasso.get().load(Uri.parse("https://avatars.mds.yandex.net/i?id=2d58fd1064718fe4e9566ff010be739081329b4e-8507274-images-thumbs&n=13")).into(binding.btnPopularProd5);
        Picasso.get().load(Uri.parse("https://media.greenmatters.com/brand-img/r1d1wrqJo/0x0/rawhide-1629837679942.jpg")).into(binding.btnPopularProd6);
        Picasso.get().load(Uri.parse("https://meuamigopet.com.br/wp-content/uploads/2020/03/produtos.png")).into(binding.btnPopularProd1);

        binding.btnPopularProd1.setOnClickListener(this);
        binding.btnPopularProd2.setOnClickListener(this);
        binding.btnPopularProd3.setOnClickListener(this);
        binding.btnPopularProd4.setOnClickListener(this);
        binding.btnPopularProd5.setOnClickListener(this);
        binding.btnPopularProd6.setOnClickListener(this);

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
        adapter = new AdapterProdutos(produtos, getContext(), true, false);
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


    @Override
    public void onClick(View view) {

        Intent i = new Intent(requireActivity(), ExibirProdutoActivity.class);

        if ( view.getId() == getBinding().btnPopularProd1.getId()){
            i.putExtra("id", 0);
        }else if (view.getId() == getBinding().btnPopularProd2.getId()){
            i.putExtra("id", 1);
        }else if (view.getId() == getBinding().btnPopularProd3.getId()){
            i.putExtra("id", 2);
        }else if (view.getId() == getBinding().btnPopularProd4.getId()){
            i.putExtra("id", 3);
        }else if (view.getId() == getBinding().btnPopularProd5.getId()){
            i.putExtra("id", 4);
        }else if (view.getId() == getBinding().btnPopularProd6.getId()){
            i.putExtra("id", 5);
        }

        startActivity(i);
    }
}
