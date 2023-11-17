package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import benicio.soluces.tccpetshop.databinding.ActivityCredenciamentoBinding;
import benicio.soluces.tccpetshop.model.ClientModel;

public class CredenciamentoActivity extends AppCompatActivity {
    // te
    ActivityCredenciamentoBinding mainBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    DatabaseReference refCliente = FirebaseDatabase.getInstance().getReference().child("user_table");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCredenciamentoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mainBinding.btnCreateAccount.setOnClickListener( view -> {
            startActivity(new Intent(getApplicationContext(), CriarContaActivity.class));
        });

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);
        editor = preferences.edit();

        if ( !Objects.requireNonNull(preferences.getString("idUsuario", "")).isEmpty()){
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(CredenciamentoActivity.this, "Bem-vindo de volta!", Toast.LENGTH_SHORT).show();
        }

        mainBinding.btnAccessAccount.setOnClickListener( view -> {
            String login, senha;

            login = mainBinding.etClientLogin.getText().toString();
            senha = mainBinding.etClientPassword.getText().toString();

            refCliente.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if ( snapshot.exists() ){
                        boolean ce = false;
                        for ( DataSnapshot dado : snapshot.getChildren()){
                            ClientModel clientModel = dado.getValue(ClientModel.class);
                            if ( clientModel.getLogin().equals(login)){
                                if (  clientModel.getSenha().equals(senha)){
                                    Toast.makeText(CredenciamentoActivity.this, "Bem-vindo de volta!", Toast.LENGTH_SHORT).show();
                                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                                    editor.putString("idUsuario", clientModel.getId());
                                    editor.apply();
                                    finish();
                                    ce = true;
                                }
                            }
                        }
                        if ( !ce ){
                            Toast.makeText(CredenciamentoActivity.this, "Credenciais erradas.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }
}