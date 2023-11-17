package benicio.soluces.tccpetshop.ui;

import android.view.View;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentPetProfileBinding;


public class FragmentPetProfile extends BaseFragment<FragmentPetProfileBinding> implements
        View.OnClickListener {

    public FragmentPetProfile() {
        super(R.layout.fragment_pet_profile, FragmentPetProfileBinding::bind);
    }

    @Override
    public void onBindCreated(FragmentPetProfileBinding binding) {
        binding.btnBack.setOnClickListener(v -> popBackStack());
    }

    @Override
    public void onClick(View view) {

    }
}
