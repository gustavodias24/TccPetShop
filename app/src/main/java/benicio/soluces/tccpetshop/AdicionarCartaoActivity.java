package benicio.soluces.tccpetshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.soluces.tccpetshop.databinding.ActivityAdicionarCartaoBinding;
import benicio.soluces.tccpetshop.databinding.ActivityPerfilPetBinding;
import benicio.soluces.tccpetshop.model.CartaoModel;

public class AdicionarCartaoActivity extends AppCompatActivity {

    ActivityAdicionarCartaoBinding mainBinding;
    SharedPreferences preferences;
    DatabaseReference refCartao = FirebaseDatabase.getInstance().getReference().child("catao_table");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityAdicionarCartaoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        mainBinding.btnBack.setOnClickListener( view -> finish() );

        mainBinding.btnCadastrar.setOnClickListener(view -> {
            CartaoModel cc = new CartaoModel();
            String idCartao = UUID.randomUUID().toString();
            cc.setId(idCartao);
            cc.setIdCliente(preferences.getString("idUsuario", ""));
            cc.setTipo(mainBinding.edtTipo.getText().toString());
            cc.setNumeroCartao(mainBinding.edtNumero.getText().toString());
            cc.setCodigoSeguranca(mainBinding.edtCodigo.getText().toString());
            cc.setDataVencimento(mainBinding.edtData.getText().toString());
            cc.setNomeCartao(mainBinding.edtNomeCartao.getText().toString());

            refCartao.child(preferences.getString("idUsuario", "")).child(idCartao).setValue(cc).addOnCompleteListener( task -> {
                if ( task.isSuccessful()){
                    Toast.makeText(this, "Cartão adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Erro de conexão", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}