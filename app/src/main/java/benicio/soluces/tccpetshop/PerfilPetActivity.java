package benicio.soluces.tccpetshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.soluces.tccpetshop.databinding.ActivityPerfilPetBinding;
import benicio.soluces.tccpetshop.databinding.ActivityPerfilUsuarioBinding;
import benicio.soluces.tccpetshop.model.PetModel;

public class PerfilPetActivity extends AppCompatActivity {

    ActivityPerfilPetBinding mainBinding;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseReference refPet = FirebaseDatabase.getInstance().getReference().child("pet_table");
    PetModel petModel = new PetModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityPerfilPetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);
        editor = preferences.edit();

        mainBinding.btnBack.setOnClickListener( view -> finish());

        if ( !preferences.getString("idPet", "").isEmpty()){
            refPet.child(preferences.getString("idUsuario", "")).child(preferences.getString("idPet", "")).get().addOnCompleteListener(
                    task -> {
                        if ( task.isSuccessful() ){
                            PetModel petModel = task.getResult().getValue(PetModel.class);
                            mainBinding.etClientName.setText(petModel.getNomeCliente());
                            mainBinding.etRaca.setText(petModel.getRaca());
                            mainBinding.etPeso.setText(petModel.getPeso());
                            mainBinding.etAlergia.setText(petModel.getAlergia());
                        }else{
                            Toast.makeText(this, "Erro de conexão!", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

        mainBinding.btnRegister.setOnClickListener(view -> {

            petModel.setNomeCliente(mainBinding.etClientName.getText().toString());
            petModel.setRaca(mainBinding.etRaca.getText().toString());
            petModel.setPeso(mainBinding.etPeso.getText().toString());
            petModel.setAlergia(mainBinding.etAlergia.getText().toString());
            petModel.setIdCliente(preferences.getString("idUsuario", ""));


            if ( !preferences.getString("idPet", "").isEmpty() ) {
                petModel.setId(preferences.getString("idPet", ""));
                // vai  atualizar
                refPet.child(preferences.getString("idUsuario", "")).child(preferences.getString("idPet", "")).setValue(
                        petModel
                ).addOnCompleteListener(task -> {
                    if ( task.isSuccessful() ){
                        Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Problema de conexão!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                // Adicionar pet
                String idPet = UUID.randomUUID().toString();
                petModel.setId(idPet);
                editor.putString("idPet", idPet).apply();
                refPet.child(preferences.getString("idUsuario", "")).child(idPet).setValue(
                        petModel
                ).addOnCompleteListener( task -> {
                    if ( task.isSuccessful() ){
                        Toast.makeText(this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Problema de conexão!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}