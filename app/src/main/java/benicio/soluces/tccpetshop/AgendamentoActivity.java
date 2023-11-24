package benicio.soluces.tccpetshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import benicio.soluces.tccpetshop.databinding.ActivityAgendamentoBinding;
import benicio.soluces.tccpetshop.databinding.ActivityCriarContaBinding;
import benicio.soluces.tccpetshop.model.AgendamentoModel;

public class AgendamentoActivity extends AppCompatActivity {

    private ActivityAgendamentoBinding mainBinding;

    SharedPreferences preferences;
    DatabaseReference refAgendaments = FirebaseDatabase.getInstance().getReference().child("agendaments_table");

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityAgendamentoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        b = getIntent().getExtras();
        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        mainBinding.voltar.setOnClickListener( view -> finish());

        LocalDateTime dataHoraAtual = LocalDateTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String[] dataHoraFormatada = dataHoraAtual.format(formato).split(" ");

        mainBinding.edtData.setText(dataHoraFormatada[0]);
        mainBinding.edtHora.setText(dataHoraFormatada[1]);
        mainBinding.edtEstabelecimento.setText(b.getString("estabelecimento"));

        mainBinding.agendar.setOnClickListener( view -> {
            String nomePet, estabelecimento, id, idClient, data, hora;

            id = UUID.randomUUID().toString();
            idClient = preferences.getString("idUsuario", "");
            nomePet = mainBinding.edtNomePet.getText().toString();
            estabelecimento = mainBinding.edtEstabelecimento.getText().toString();
            data = mainBinding.edtData.getText().toString();
            hora = mainBinding.edtHora.getText().toString();

            refAgendaments.child(idClient).child(id).setValue(
                    new AgendamentoModel(String.format("%s às %s", data, hora), id, idClient, nomePet, estabelecimento)
            ).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    mainBinding.edtNomePet.setText("");
                    mainBinding.edtEstabelecimento.setText("");
                    mainBinding.edtData.setText("");
                    Toast.makeText(getApplicationContext(), "Agendamento criado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Problema de conexão", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}