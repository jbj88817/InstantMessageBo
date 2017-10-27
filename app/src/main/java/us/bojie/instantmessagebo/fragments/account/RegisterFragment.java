package us.bojie.instantmessagebo.fragments.account;


import android.content.Context;

import us.bojie.common.app.PresenterFragment;
import us.bojie.factory.presenter.account.RegisterContract;
import us.bojie.factory.presenter.account.RegisterPresenter;
import us.bojie.instantmessagebo.R;

/**
 * 注册的界面
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {

    private AccountTrigger mAccountTrigger;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
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

    @Override
    public void registerSuccess() {

    }
}
