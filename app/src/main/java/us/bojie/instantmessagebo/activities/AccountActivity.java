package us.bojie.instantmessagebo.activities;

import android.content.Context;
import android.content.Intent;

import us.bojie.common.app.Activity;
import us.bojie.common.app.Fragment;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.fragments.account.AccountTrigger;
import us.bojie.instantmessagebo.fragments.account.LoginFragment;
import us.bojie.instantmessagebo.fragments.account.RegisterFragment;

public class AccountActivity extends Activity implements AccountTrigger {

    private Fragment mCurFragment;
    private Fragment mLoginFragment;
    private Fragment mRegisterFragment;

    /**
     * 账户Activity显示的入口
     *
     * @param context Context
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();
    }

    @Override
    public void triggerView() {
        Fragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                //默认情况下为null，
                //第一次之后就不为null了
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            // 因为默认请求下mLoginFragment已经赋值，无须判断null
            fragment = mLoginFragment;
        }

        // 重新赋值当前正在显示的Fragment
        mCurFragment = fragment;

        // 切换显示
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lay_container, fragment)
                .commit();
    }
}
