package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.soluces.tccpetshop.DatabaseNameUtils;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterOrdes;
import benicio.soluces.tccpetshop.adapter.AdapterStores;
import benicio.soluces.tccpetshop.databinding.ActivityMainBinding;
import benicio.soluces.tccpetshop.databinding.FragmentLojasBinding;
import benicio.soluces.tccpetshop.databinding.LayoutFiltroLojaBinding;
import benicio.soluces.tccpetshop.model.StoreModel;


public class FragmentLojas extends Fragment {

    Dialog d;
    DatabaseReference refStores = FirebaseDatabase.getInstance().getReference().child(DatabaseNameUtils.stores_table);
    List<StoreModel> stores = new ArrayList<>();

    FragmentLojasBinding mainBinding;

    RecyclerView r;
    AdapterStores adapter;
    public FragmentLojas() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = FragmentLojasBinding.inflate(getLayoutInflater());

        configurarAlertFiltro();
        mainBinding.filtrar.setOnClickListener( view -> {
            d.show();
        });

        refStores.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists() ){
                    configurarRecyclerLojas();

                    stores.clear();

                    for ( DataSnapshot dado : snapshot.getChildren()){
                        stores.add(dado.getValue(StoreModel.class));
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mainBinding.getRoot();
    }
    private void configurarAlertFiltro(){
        AlertDialog.Builder b = new AlertDialog.Builder(requireActivity());
        b.setTitle("Filtrar lojas.");
        b.setMessage("Digite o raio de distância que deseja encontrar lojas.");
        LayoutFiltroLojaBinding bindingDialog = LayoutFiltroLojaBinding.inflate(getLayoutInflater());

        bindingDialog.pronto.setOnClickListener( view -> {
            Toast.makeText(requireActivity(), "Filtrando...", Toast.LENGTH_SHORT).show();
            refStores.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if ( snapshot.exists() ){
                        configurarRecyclerLojas();

                        stores.clear();

                        for ( DataSnapshot dado : snapshot.getChildren()){
                            if ( !bindingDialog.filtroEdt.getText().toString().isEmpty() ){
                                 StoreModel storeModel = dado.getValue(StoreModel.class);
                                int distanciaDesejada = Integer.parseInt(bindingDialog.filtroEdt.getText().toString());

                                assert storeModel != null;
                                if ( storeModel.getRaio() <= distanciaDesejada){
                                    stores.add(storeModel);
                                }
                            }else {
                                stores.add(dado.getValue(StoreModel.class));
                            }
                        }
                        d.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        b.setView(bindingDialog.getRoot());
        d = b.create();
    }
    private void configurarRecyclerLojas(){
        r = mainBinding.recyclerLojas;
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        r.addItemDecoration(new DividerItemDecoration(  requireContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapter = new AdapterStores(stores, getContext());
        r.setAdapter(adapter);
    }
}