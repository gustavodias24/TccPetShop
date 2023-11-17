package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentExplorerBinding;


public class FragmentExplorer extends BaseFragment<FragmentExplorerBinding> {

    public FragmentExplorer() { super(R.layout.fragment_explorer, FragmentExplorerBinding::bind);}

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindCreated(FragmentExplorerBinding binding) {

        binding.imgAdvertising.setImageDrawable(getResources().getDrawable(R.drawable.propaganda));
    }
}
