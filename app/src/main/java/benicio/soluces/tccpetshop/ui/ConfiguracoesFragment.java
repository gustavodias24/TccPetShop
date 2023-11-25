package benicio.soluces.tccpetshop.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import benicio.soluces.tccpetshop.CredenciamentoActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentConfiguracoesBinding;


public class ConfiguracoesFragment extends Fragment {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FragmentConfiguracoesBinding mainBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mainBinding = FragmentConfiguracoesBinding.inflate(getLayoutInflater());

        preferences = getActivity().getSharedPreferences("usuario", MODE_PRIVATE);
        editor = preferences.edit();

        mainBinding.sair.setOnClickListener( view -> {
            editor.putString("idUsuario", "").apply();
            Toast.makeText(requireActivity(), "Até logo!", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
            startActivity(new Intent(requireActivity(), CredenciamentoActivity.class));
        });

        return mainBinding.getRoot();
    }
}