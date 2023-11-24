package benicio.soluces.tccpetshop.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationBarView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment {


    FragmentHomeBinding mainBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                requireActivity().getSupportFragmentManager(), FragmentPagerItems.with(requireActivity())
                .add("Explorer", FragmentExplorer.class)
                .add("Histórico", FragmentListagemHome.class)
                .add("Carrinho", FragmentCart.class)
                .add("Lojas", FragmentLojas.class)
                .add("Configurações", FragmentLojas.class)
                .create());

        ViewPager viewPager = (ViewPager) mainBinding.viewpager;
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) mainBinding.viewpagertab;
        viewPagerTab.setViewPager(viewPager);

        return mainBinding.getRoot();
    }
}
