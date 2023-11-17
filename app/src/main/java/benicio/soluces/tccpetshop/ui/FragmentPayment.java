package benicio.soluces.tccpetshop.ui;

import android.view.View;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.databinding.FragmentPaymentBinding;


public class FragmentPayment extends BaseFragment<FragmentPaymentBinding> implements
        View.OnClickListener {

    public FragmentPayment() {
        super(R.layout.fragment_payment, FragmentPaymentBinding::bind);
    }

    @Override
    public void onBindCreated(FragmentPaymentBinding binding) {
        binding.btnBack.setOnClickListener(v -> popBackStack());
        binding.imgPaymentToggle.setOnClickListener(
                v -> togglePaymentVisibility(getBinding().clAddCardInfo, binding.imgPaymentToggle));
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.img_payment_toggle:
//        }
    }

    private void togglePaymentVisibility(View v1, View v2) {
        toggleVisibilityAndRotate(!(v1.getVisibility() == View.VISIBLE), v1, v2);
    }


    public static void toggleVisibilityAndRotate(boolean status, View view1, View view2) {
        if (status) {
            view1.setVisibility(View.VISIBLE);
            view2.animate().rotation(180f);
        } else {
            view1.setVisibility(View.GONE);
            view2.animate().rotation(0f);
        }
    }
}
