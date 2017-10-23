package us.bojie.instantmessagebo.activities;

import android.content.Intent;

import us.bojie.common.app.Activity;
import us.bojie.common.app.Fragment;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.fragments.user.UpdateInfoFragment;

public class UserActivity extends Activity {

    private Fragment mCurFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();
    }

    // Activity中收到剪切图片成功的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCurFragment.onActivityResult(requestCode, resultCode, data);
    }
}
