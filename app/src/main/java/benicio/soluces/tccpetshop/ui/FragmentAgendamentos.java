package benicio.soluces.tccpetshop.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentAgendamentosBinding;
import benicio.soluces.tccpetshop.model.AgendamentoModel;

public class FragmentAgendamentos extends Fragment {


    SharedPreferences preferences;
    DatabaseReference refAgendaments = FirebaseDatabase.getInstance().getReference().child("agendaments_table");
    FragmentAgendamentosBinding mainBinding;

    public static FragmentAgendamentos newInstance(String param1, String param2) {
        FragmentAgendamentos fragment = new FragmentAgendamentos();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = FragmentAgendamentosBinding.inflate(getLayoutInflater());

        preferences = getActivity().getSharedPreferences("usuario", MODE_PRIVATE);

        mainBinding.agendar.setOnClickListener( view -> {
            String nomePet, estabelecimento, id, idClient, dataHora;

            id = UUID.randomUUID().toString();
            idClient = preferences.getString("idUsuario", "");
            nomePet = mainBinding.edtNomePet.getText().toString();
            estabelecimento = mainBinding.edtEstabelecimento.getText().toString();
            dataHora = mainBinding.edtData.getText().toString();

            refAgendaments.child(idClient).child(id).setValue(
                    new AgendamentoModel(dataHora, id, idClient, nomePet, estabelecimento)
            ).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    mainBinding.edtNomePet.setText("");
                    mainBinding.edtEstabelecimento.setText("");
                    mainBinding.edtData.setText("");
                    Toast.makeText(getActivity(), "Agendamento criado com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Problema de conexão", Toast.LENGTH_SHORT).show();
                }
            });
        });
        return mainBinding.getRoot();
    }
}