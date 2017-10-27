package us.bojie.instantmessagebo.fragments.account;


import android.content.Context;
import android.view.View;
import android.widget.EditText;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.PresenterFragment;
import us.bojie.factory.presenter.account.RegisterContract;
import us.bojie.factory.presenter.account.RegisterPresenter;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.activities.MainActivity;

/**
 * 注册的界面
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {

    @BindView(R.id.et_phone)
    EditText mPhone;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.btn_submit)
    Button mSubmit;
    @BindView(R.id.loading)
    Loading mLoading;

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
        // 注册成功，这个时候账户已经登录
        // 我们需要进行跳转到MainActivity界面
        MainActivity.show(getContext());
        // 关闭当前界面
        getActivity().finish();
    }

    @OnClick({R.id.tv_go_login, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_login:
                // 让AccountActivity进行界面切换
                mAccountTrigger.triggerView();
                break;
            case R.id.btn_submit:
                String phone = mPhone.getText().toString();
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.register(phone, name, password);
                break;
        }
    }

    @Override
    public void showLoading() {
        super.showLoading();
        // 正在进行时，正在进行注册，界面不可操作
        // 开始Loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);
        mName.setEnabled(false);
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
        mName.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        mSubmit.setEnabled(true);
    }
}
