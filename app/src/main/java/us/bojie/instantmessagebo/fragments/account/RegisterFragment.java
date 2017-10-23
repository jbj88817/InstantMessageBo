package us.bojie.instantmessagebo.fragments.account;


import android.content.Context;

import us.bojie.common.app.Fragment;
import us.bojie.instantmessagebo.R;

/**
 * 注册的界面
 */
public class RegisterFragment extends Fragment {

    private AccountTrigger mAccountTrigger;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }
}
