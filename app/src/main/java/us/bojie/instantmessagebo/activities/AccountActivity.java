package us.bojie.instantmessagebo.activities;

import android.content.Context;
import android.content.Intent;

import us.bojie.common.app.Activity;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.fragments.account.UpdateInfoFragment;

public class AccountActivity extends Activity {

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
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, new UpdateInfoFragment())
                .commit();
    }
}
