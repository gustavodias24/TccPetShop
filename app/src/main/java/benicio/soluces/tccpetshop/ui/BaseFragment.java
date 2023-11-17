package benicio.soluces.tccpetshop.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.function.Function;

public abstract class BaseFragment<Binding> extends Fragment {
    private final Function<View, Binding> mBindingGenerator;
    private Binding mBinding = null;

    protected BaseFragment(@LayoutRes int contentLayoutId,
                           Function<View, Binding> bindingGenerator) {
        super(contentLayoutId);
        mBindingGenerator = bindingGenerator;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding = mBindingGenerator.apply(view);
        }
        onBindCreated(mBinding);
    }

    public abstract void onBindCreated(Binding binding);

    protected Binding getBinding() {
        return mBinding;
    }

    protected void popBackStack() {
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}
