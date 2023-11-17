package benicio.soluces.tccpetshop.ui;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationBarView;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentHomeBinding;

public class FragmentHome extends BaseFragment<FragmentHomeBinding> implements
        NavigationBarView.OnItemSelectedListener {


    public FragmentHome() {
        super(R.layout.fragment_home, FragmentHomeBinding::bind);
    }

    @Override
    public void onBindCreated(FragmentHomeBinding binding) {
        binding.navMenu.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem v) {
        Fragment fragment = null;
        if ( v.getItemId() == R.id.btn_explorer){
            fragment = new FragmentExplorer();
        }else if (v.getItemId() ==  R.id.btn_cart){
            fragment = new FragmentCart();
        }else if (v.getItemId() == R.id.btn_wish_list){
            fragment = new FragmentWishlist();
        }else if (v.getItemId() == R.id.btn_profile){
            fragment = new FragmentSettings();
        }else if ( v.getItemId() == R.id.btn_lojas_proximas){
            fragment = new FragmentLojas();
        }
        inflateFragment(fragment);
        return false;
    }

    private void inflateFragment(Fragment fragment) {
        if (fragment == null) return;
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_layout, fragment, null)
                .addToBackStack(null).commit();
    }
}
