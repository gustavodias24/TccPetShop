package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import benicio.soluces.tccpetshop.databinding.ActivityExibirProdutoBinding;
import benicio.soluces.tccpetshop.model.ProductModel;

public class ExibirProdutoActivity extends AppCompatActivity {

    ActivityExibirProdutoBinding binding;
    Bundle b;

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
                    ProductModel productModel = snapshot.getValue(ProductModel.class);
                    Picasso.get().load(productModel.getImagem()).into(binding.fotoProduto);
                    binding.infoProduto.setText(productModel.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}