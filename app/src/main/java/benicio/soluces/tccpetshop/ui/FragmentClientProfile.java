package benicio.soluces.tccpetshop.ui;

import android.annotation.SuppressLint;
import android.view.View;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentClientProfileBinding;


public class FragmentClientProfile extends BaseFragment<FragmentClientProfileBinding> implements
        View.OnClickListener {

    public FragmentClientProfile() {
        super(R.layout.fragment_client_profile, FragmentClientProfileBinding::bind);
    }

    @Override
    public void onBindCreated(FragmentClientProfileBinding binding) {
        binding.btnBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if ( view.getId() == R.id.btn_back){
            popBackStack();
        }
    }
}
