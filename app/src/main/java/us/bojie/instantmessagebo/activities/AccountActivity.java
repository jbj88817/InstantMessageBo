package us.bojie.instantmessagebo.activities;

import android.content.Context;
import android.content.Intent;

import us.bojie.common.app.Activity;
import us.bojie.common.app.Fragment;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.fragments.account.LoginFragment;

public class AccountActivity extends Activity {

    private Fragment mCurFragment;
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

        mCurFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();
    }
}
