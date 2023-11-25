package benicio.soluces.tccpetshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import benicio.soluces.tccpetshop.ui.ConfiguracoesFragment;
import benicio.soluces.tccpetshop.ui.FragmentCart;
import benicio.soluces.tccpetshop.ui.FragmentExplorer;
import benicio.soluces.tccpetshop.ui.FragmentListagemHome;
import benicio.soluces.tccpetshop.ui.FragmentLojas;

public class AdapterHomePrincipal extends FragmentStateAdapter {

    public AdapterHomePrincipal(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentExplorer();
            case 1:
                return new FragmentListagemHome();
            case 2:
                return new FragmentCart();
            case 3:
                return new FragmentLojas();
            case 4:
                return new ConfiguracoesFragment();
            default:
                return null;
        }
    }



    @Override
    public int getItemCount() {
        return 5;
    }
}