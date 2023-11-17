package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
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
import benicio.soluces.tccpetshop.model.StoreModel;


public class FragmentLojas extends Fragment {

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

        refStores.addValueEventListener(new ValueEventListener() {
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

    private void configurarRecyclerLojas(){
        r = mainBinding.recyclerLojas;
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        r.addItemDecoration(new DividerItemDecoration(  requireContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapter = new AdapterStores(stores, getContext());
        r.setAdapter(adapter);
    }
}