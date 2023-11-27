package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import benicio.soluces.tccpetshop.databinding.ActivityExibirProdutoBinding;
import benicio.soluces.tccpetshop.model.ProductModel;
import benicio.soluces.tccpetshop.utils.CarrinhoUtils;

public class ExibirProdutoActivity extends AppCompatActivity {

    ActivityExibirProdutoBinding binding;
    Bundle b;
    ProductModel productModel;
    DatabaseReference refProduct = FirebaseDatabase.getInstance().getReference().child("product_table");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExibirProdutoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.btnBack.setOnClickListener( view -> finish());
        b = getIntent().getExtras();

        Log.d("benicius", "onCreate: " + b.getInt("id"));
        assert b != null;
        refProduct.child(b.getInt("id") + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists() ){
                    productModel = snapshot.getValue(ProductModel.class);
                    Picasso.get().load(productModel.getImagem()).into(binding.fotoProduto);
                    binding.infoProduto.setText(productModel.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnAdicionar.setOnClickListener( view -> {
            if ( productModel != null){
                int quantidade = binding.edQuantidadeCarrinho.getText().toString().isEmpty() ? 0 : Integer.parseInt(
                        binding.edQuantidadeCarrinho.getText().toString()
                );

                for( int i = quantidade ; i > 0 ; i--){
                    List<ProductModel> listaAntiga = CarrinhoUtils.returnCarrinho(getApplicationContext());
                    listaAntiga.add(productModel);
                    CarrinhoUtils.saveCarrinho(listaAntiga, getApplicationContext());
                }
                Toast.makeText(this, "Adicionado ao carrinho", Toast.LENGTH_LONG).show();

            }
        });
    }
}