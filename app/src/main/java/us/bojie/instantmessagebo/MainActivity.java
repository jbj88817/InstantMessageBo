package us.bojie.instantmessagebo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.Activity;
import us.bojie.common.widget.PortraitView;
import us.bojie.instantmessagebo.fragments.main.ActiveFragment;
import us.bojie.instantmessagebo.fragments.main.GroupFragment;

public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.appbar)
    View mLayAppbar;
    @BindView(R.id.iv_portrait)
    PortraitView mPortrait;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.lay_container)
    FrameLayout mContainer;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mNavigation.setOnNavigationItemSelectedListener(this);

        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mLayAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.iv_search, R.id.btn_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.btn_action:
                break;
        }
    }

    boolean isFirst = true;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            mTitle.setText(R.string.title_home);
            ActiveFragment activeFragment = new ActiveFragment();
            if (isFirst) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, activeFragment)
                        .commit();
                isFirst = false;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, activeFragment)
                        .commit();
            }

        } else if (item.getItemId() == R.id.action_group) {
            mTitle.setText(R.string.title_group);
            GroupFragment groupFragment = new GroupFragment();
            if (isFirst) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, groupFragment)
                        .commit();
                isFirst = false;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, groupFragment)
                        .commit();
            }

        }

        mTitle.setText(item.getTitle());
        return true;
    }
}
