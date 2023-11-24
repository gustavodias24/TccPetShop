package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.tccpetshop.adapter.AdapterProdutos;
import benicio.soluces.tccpetshop.databinding.ActivityExibirLojaBinding;
import benicio.soluces.tccpetshop.databinding.ActivityExibirProdutoBinding;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.model.StoreModel;

public class ExibirLojaActivity extends AppCompatActivity {

    private ActivityExibirLojaBinding binding;
    private DatabaseReference refStores = FirebaseDatabase.getInstance().getReference().child(DatabaseNameUtils.stores_table);
    private DatabaseReference refProduct = FirebaseDatabase.getInstance().getReference().child("product_table");

    Bundle b;
    RecyclerView r;
    AdapterProdutos adapter;
    List<ProductModel> produtos = new ArrayList<>();

    StoreModel storeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExibirLojaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        b = getIntent().getExtras();

        binding.voltar.setOnClickListener( view -> finish());

        binding.agendar.setOnClickListener( view -> {
            Intent i = new Intent(getApplicationContext(), AgendamentoActivity.class);
            if ( storeModel != null){
                i.putExtra("estabelecimento", storeModel.getNomeLoja());
            }
            startActivity(i);
        });
        refStores.child(b.getInt("id", 0) + "").get().addOnCompleteListener(task -> {
            if ( task.isSuccessful() ){
                storeModel = task.getResult().getValue(StoreModel.class);
                binding.textDescri.setText(storeModel.getDescricao());
                binding.textDistancia.setText(String.format("Estabelecimento a uma distância de %dm de você.", storeModel.getRaio()));
                binding.textAgenda.setText(storeModel.getAgenda());
                binding.textServicos.setText(storeModel.getServicosListaado());

                configurarListenerProduto();
            }
        });

        configurarRecyclerView();

    }

    private void configurarListenerProduto(){
        refProduct.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() ){
                    produtos.clear();

                    for ( DataSnapshot dado : snapshot.getChildren()){
                        ProductModel productModel = dado.getValue(ProductModel.class);
                        if ( storeModel.getProdutos().contains(productModel.getId())) {
                            produtos.add(productModel);
                        }
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void configurarRecyclerView(){
        r = binding.recyclerProdutos;
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        r.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapter = new AdapterProdutos(produtos, getApplicationContext(), true, false);
        r.setAdapter(adapter);
    }
}