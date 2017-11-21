package us.bojie.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import us.bojie.common.convention.PlaceHolderView;

/**
 * Created by bojiejiang on 8/12/17.
 */

public abstract class Activity extends AppCompatActivity {

    protected PlaceHolderView mPlaceHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initBefore();
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {

    }

    protected void initWindows() {

    }

    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    protected abstract int getContentLayoutId();

    protected void initWidget() {
        ButterKnife.bind(this);
    }

    protected void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @SuppressWarnings("RestrictedApi")
    @Override
    public void onBackPressed() {
        // 得到当前Activity下所有Fragment
        List<android.support.v4.app.Fragment> fragments = getSupportFragmentManager().getFragments();
        // 判断是否为空
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                // 判断是否为我们处理的fragment类型
                if (fragment instanceof us.bojie.common.app.Fragment) {
                    // 判断是否拦截了返回
                    if (((us.bojie.common.app.Fragment) fragment).onBackPressed()) {
                        return;
                    }
                }
            }
        }

        super.onBackPressed();
        finish();
    }

    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        mPlaceHolderView = placeHolderView;
    }

}
