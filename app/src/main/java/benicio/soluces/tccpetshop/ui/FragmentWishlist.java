package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterOrdes;
import benicio.soluces.tccpetshop.databinding.FragmentWishListBinding;
import benicio.soluces.tccpetshop.model.AgendamentoModel;
import benicio.soluces.tccpetshop.model.OrderModel;


public class FragmentWishlist extends BaseFragment<FragmentWishListBinding> implements
        View.OnClickListener {

    SharedPreferences preferences ;
    DatabaseReference refPedidos = FirebaseDatabase.getInstance().getReference().child("agendaments_table");
    AdapterOrdes adapterOrdes;
    RecyclerView r;
    List<AgendamentoModel> orders = new ArrayList<>();
    public FragmentWishlist() { super(R.layout.fragment_wish_list, FragmentWishListBinding::bind); }

    @Override
    public void onBindCreated(FragmentWishListBinding binding) {
//        binding.btnBack.setOnClickListener(v -> popBackStack());
        cconfigurarRecycler();
        preferences = getContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        refPedidos.child(preferences.getString("idUsuario", "")).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists() ){
                    orders.clear();

                    for ( DataSnapshot dado : snapshot.getChildren()){
                        orders.add(dado.getValue(AgendamentoModel.class));
                    }

                    adapterOrdes.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void cconfigurarRecycler(){
        r = getBinding().recyclerPedidos;
        r.setLayoutManager(new LinearLayoutManager(getActivity()));
        r.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        adapterOrdes = new AdapterOrdes(orders,getActivity());
        r.setAdapter(adapterOrdes);
    }
}
