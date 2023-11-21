package benicio.soluces.tccpetshop.ui;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentListagemAgendamentoCompraBinding;


public class FragmentListagemHome extends BaseFragment<FragmentListagemAgendamentoCompraBinding> implements
        NavigationBarView.OnItemSelectedListener{

    public FragmentListagemHome() {
        super(R.layout.fragment_listagem_agendamento_compra, FragmentListagemAgendamentoCompraBinding::bind);
    }


    @Override
    public void onBindCreated(FragmentListagemAgendamentoCompraBinding binding) {
        binding.navMenu.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem v) {

        Fragment fragment = null;
        if ( v.getItemId() == R.id.btn_listagem_agendamentos){
            fragment = new FragmentWishlist();
        }else if (v.getItemId() == R.id.btn_listagem_compras){
            fragment = new FragmentHistoricoCompras();
        }

        if ( fragment != null){
            inflateFragment(fragment);
        }
        return false;
    }

    private void inflateFragment(Fragment fragment) {
        if (fragment == null) return;
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_layout, fragment, null)
                .addToBackStack(null).commit();
    }

}