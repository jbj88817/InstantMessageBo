package us.bojie.instantmessagebo.fragments.account;


import android.content.Context;
import android.view.View;
import android.widget.EditText;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.PresenterFragment;
import us.bojie.factory.presenter.account.LoginContract;
import us.bojie.factory.presenter.account.LoginPresenter;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.activities.MainActivity;

/**
 * 登录的界面
 */
public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View {

    @BindView(R.id.edit_phone)
    EditText mPhone;
    @BindView(R.id.edit_password)
    EditText mPassword;
    @BindView(R.id.btn_submit)
    Button mSubmit;
    @BindView(R.id.loading)
    Loading mLoading;

    private AccountTrigger mAccountTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void loginSuccess() {
        MainActivity.show(getContext());
        getActivity().finish();
    }

    @OnClick({R.id.txt_go_register, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_go_register:
                // 让AccountActivity进行界面切换
                mAccountTrigger.triggerView();
                break;
            case R.id.btn_submit:
                String phone = mPhone.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.login(phone, password);
                break;
        }
    }

    @Override
    public void showLoading() {
        super.showLoading();
        // 正在进行时，界面不可操作
        // 开始Loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 当需要显示错误的时候触发，一定是结束了
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        mSubmit.setEnabled(true);
    }
}
