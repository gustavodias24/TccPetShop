package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.soluces.tccpetshop.databinding.ActivityCriarContaBinding;
import benicio.soluces.tccpetshop.model.ClientModel;

public class CriarContaActivity extends AppCompatActivity {

    ActivityCriarContaBinding mainBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseReference refCliente = FirebaseDatabase.getInstance().getReference().child("user_table");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCriarContaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);
        editor = preferences.edit();

        mainBinding.btnBack.setOnClickListener(view -> {
            finish();
        });

        mainBinding.btnRegisterClient.setOnClickListener( view -> {
            String nomeSocial, cpf, dataNascimento, email, telefone, endereco, numero, pontoRef, cep, login, senha, id;

            nomeSocial = mainBinding.etClientName.getText().toString();
            cpf = mainBinding.etClientSocialNumber.getText().toString();
            dataNascimento = mainBinding.etBirthDate.getText().toString();
            email = mainBinding.etClientEmail.getText().toString();
            telefone = mainBinding.etClientNumber.getText().toString();
            endereco = mainBinding.etClientAddress.getText().toString();
            numero = mainBinding.etClientAddressNumber.getText().toString();
            pontoRef = mainBinding.etReferencePoint.getText().toString();
            cep = mainBinding.etClientCep.getText().toString();
            login = mainBinding.etClientLogin.getText().toString();
            senha = mainBinding.etClientPassword.getText().toString();

            id = UUID.randomUUID().toString();

            refCliente.child(id).setValue(
                    new ClientModel(id, nomeSocial, cpf, dataNascimento, email, telefone, endereco, numero, pontoRef, cep, login, senha)
            ).addOnCompleteListener(task -> {
                if ( task.isSuccessful() ){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    editor.putString("idUsuario", id);
                    editor.apply();
                    Toast.makeText(CriarContaActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CriarContaActivity.this, "Erro ao fazer conexão!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}