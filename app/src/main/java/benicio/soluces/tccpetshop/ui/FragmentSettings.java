package benicio.soluces.tccpetshop.ui;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentSettingsBinding;


public class FragmentSettings extends Fragment {


//    @Override
//    public void onBindCreated(FragmentSettingsBinding binding) {
//        binding.btnClientProfile.setOnClickListener(this);
//        binding.btnPetProfile.setOnClickListener(this);
//        binding.btnPayment.setOnClickListener(this);
//    }

//    @Override
//    public void onClick(View view) {
//
//        if ( view.getId() == R.id.btn_client_profile){
//            inflateClientProfile();
//        }else if ( view.getId() == R.id.btn_pet_profile){
//            inflatePetProfile();
//        }else if ( view.getId() ==  R.id.btn_payment){
//            inflatePaymentFragment();
//        }
//    }


//    private void inflateClientProfile() {
//        FragmentClientProfile profile = new FragmentClientProfile();
//        FragmentManager fm = getParentFragmentManager();
//        fm.beginTransaction().replace(R.id.fragment_layout, profile, null)
//                .addToBackStack(null)
//                .commit();
//    }
//
//    private void inflatePetProfile() {
//        FragmentPetProfile petProfile = new FragmentPetProfile();
//        FragmentManager fm = getParentFragmentManager();
//        fm.beginTransaction().replace(R.id.fragment_layout, petProfile, null)
//                .addToBackStack(null)
//                .commit();
//    }
//
//    private void inflatePaymentFragment() {
//        FragmentPayment payment = new FragmentPayment();
//        FragmentManager fm = getParentFragmentManager();
//        fm.beginTransaction().replace(R.id.fragment_layout, payment, null)
//                .addToBackStack(null)
//                .commit();
//    }
}
