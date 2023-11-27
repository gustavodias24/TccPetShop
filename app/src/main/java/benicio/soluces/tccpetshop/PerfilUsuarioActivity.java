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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.soluces.tccpetshop.databinding.ActivityCriarContaBinding;
import benicio.soluces.tccpetshop.databinding.ActivityPerfilUsuarioBinding;
import benicio.soluces.tccpetshop.model.ClientModel;

public class PerfilUsuarioActivity extends AppCompatActivity {

    ActivityPerfilUsuarioBinding mainBinding;

    SharedPreferences preferences;
    DatabaseReference refCliente = FirebaseDatabase.getInstance().getReference().child("user_table");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityPerfilUsuarioBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mainBinding.btnBack.setOnClickListener( view -> finish() );

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        refCliente.child( preferences.getString("idUsuario", "")).get().addOnCompleteListener(task -> {
            if ( task.isSuccessful() ){
                ClientModel client = task.getResult().getValue(ClientModel.class);

                mainBinding.etClientName.setText(client.getNomeSocial());
                mainBinding.etClientSocialNumber.setText(client.getCpf());
                mainBinding.etBirthDate.setText(client.getDataNascimento());
                mainBinding.etClientEmail.setText(client.getEmail());
                mainBinding.etClientNumber.setText(client.getTelefone());
                mainBinding.etClientAddress.setText(client.getEndereco());
                mainBinding.etClientAddressNumber.setText(client.getNumero());
                mainBinding.etReferencePoint.setText(client.getPontoRef());
                mainBinding.etClientLogin.setText(client.getLogin());
                mainBinding.etClientPassword.setText(client.getSenha());

            }   else{
                Toast.makeText(this, "Erro de conexão.", Toast.LENGTH_SHORT).show();
            }
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


            refCliente.child(preferences.getString("idUsuario", "")).setValue(
                    new ClientModel(preferences.getString("idUsuario", ""), nomeSocial, cpf, dataNascimento, email, telefone, endereco, numero, pontoRef, cep, login, senha)
            ).addOnCompleteListener(task -> {
                if ( task.isSuccessful() ){
                    Toast.makeText(this, "Dados atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erro ao fazer conexão!", Toast.LENGTH_SHORT).show();
                }
            });
        });



    }
}