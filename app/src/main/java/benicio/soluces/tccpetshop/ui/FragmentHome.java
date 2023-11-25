package benicio.soluces.tccpetshop.ui;


import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import benicio.soluces.tccpetshop.CredenciamentoActivity;
import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.adapter.AdapterHomePrincipal;
import benicio.soluces.tccpetshop.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment {


    FragmentHomeBinding mainBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainBinding = FragmentHomeBinding.inflate(getLayoutInflater());


        ViewPager2 viewPager = mainBinding.viewPager;
        TabLayout tabLayout = mainBinding.tabLayout;

        viewPager.setUserInputEnabled(false);

        AdapterHomePrincipal viewPagerAdapter = new AdapterHomePrincipal(requireActivity());
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
//                    tab.setText("Explorer");
                    tab.setIcon(R.drawable.ic_search);
                    break;
                case 1:
//                    tab.setText("Histórico");
                    tab.setIcon(R.drawable.ic_wish_list);
                    break;
                case 2:
//                    tab.setText("Carrinho");
                    tab.setIcon(R.drawable.ic_shopping_cart);
                    break;
                case 3:
//                    tab.setText("Lojas");
                    tab.setIcon(R.drawable.baseline_add_home_work_24);
                    break;
                case 4:
//                    tab.setText("Configurações");
                    tab.setIcon(R.drawable.baseline_window_24);
                    break;
            }
        }).attach();

        return mainBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("usuario", MODE_PRIVATE);

        if( preferences.getString("idUsuario", "").isEmpty()){
            requireActivity().finish();
            startActivity(new Intent(requireActivity(), CredenciamentoActivity.class));
        }
    }
}
