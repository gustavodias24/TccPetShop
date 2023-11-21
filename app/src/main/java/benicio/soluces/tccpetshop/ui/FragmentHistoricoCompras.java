package benicio.soluces.tccpetshop.ui;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterHistoricoCompras;
import benicio.soluces.tccpetshop.adapter.AdapterOrdes;
import benicio.soluces.tccpetshop.databinding.FragmentHistoricoComprasBinding;
import benicio.soluces.tccpetshop.model.PedidoCompraModel;


public class FragmentHistoricoCompras extends Fragment {
    SharedPreferences preferences;
   DatabaseReference orderProduct = FirebaseDatabase.getInstance().getReference().child("order_product_table");
   FragmentHistoricoComprasBinding binding;
   RecyclerView r;
   AdapterHistoricoCompras adapterHistoricoCompras;
    List<PedidoCompraModel> pedidosCompras = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHistoricoComprasBinding.inflate(getLayoutInflater());
        cconfigurarRecycler();
        binding.btnBack.setOnClickListener(v -> popBackStack());
        preferences = requireActivity().getSharedPreferences("usuario", MODE_PRIVATE);

        orderProduct.child(preferences.getString("idUsuario", "")).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.exists()){
                    pedidosCompras.clear();

                    for ( DataSnapshot dado : snapshot.getChildren()){
                        PedidoCompraModel pedidoCompraModel = dado.getValue(PedidoCompraModel.class);
                        pedidosCompras.add(pedidoCompraModel);
                    }
                    adapterHistoricoCompras.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    private void cconfigurarRecycler(){
        r = binding.recyclerCompras;
        r.setLayoutManager(new LinearLayoutManager(getActivity()));
        r.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapterHistoricoCompras = new AdapterHistoricoCompras(pedidosCompras,getActivity());
        r.setAdapter(adapterHistoricoCompras);
    }


    protected void popBackStack() {
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();
    }


}