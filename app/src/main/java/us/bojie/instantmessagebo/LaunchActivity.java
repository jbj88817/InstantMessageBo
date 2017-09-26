package us.bojie.instantmessagebo;

import us.bojie.common.app.Activity;
import us.bojie.instantmessagebo.activities.MainActivity;
import us.bojie.instantmessagebo.fragments.assist.PermissionsFragment;

public class LaunchActivity extends Activity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }
    }
}
