package us.bojie.instantmessagebo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
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
import us.bojie.instantmessagebo.fragments.main.ContactFragment;
import us.bojie.instantmessagebo.fragments.main.GroupFragment;
import us.bojie.instantmessagebo.utils.NavUtils;

public class MainActivity extends Activity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavUtils.OnTabChangedListener<Integer> {

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

    private NavUtils<Integer> mNavUtils;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mNavUtils = new NavUtils<>(this, R.id.lay_container, getSupportFragmentManager(), this);
        mNavUtils.add(R.id.action_home, new NavUtils.Tab<>(ActiveFragment.class, R.string.title_home))
                .add(R.id.action_group, new NavUtils.Tab<>(GroupFragment.class, R.string.title_group))
                .add(R.id.action_contact, new NavUtils.Tab<>(ContactFragment.class, R.string.title_contact));

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

        // 从底部导中接管我们的Menu，然后进行手动的触发第一次点击
        Menu menu = mNavigation.getMenu();
        // 触发首次选中Home
        menu.performIdentifierAction(R.id.action_home, 0);
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

    /**
     * 当我们的底部导航被点击的时候触发
     *
     * @param item MenuItem
     * @return True 代表我们能够处理这个点击
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // 转接事件流到工具类中
        return mNavUtils.performClickMenu(item.getItemId());
    }

    /**
     * NavHelper 处理后回调的方法
     *
     * @param newTab 新的Tab
     * @param oldTab 就的Tab
     */
    @Override
    public void onTabChanged(NavUtils.Tab<Integer> newTab, NavUtils.Tab<Integer> oldTab) {
        // 从额外字段中取出我们的Title资源Id
        mTitle.setText(newTab.extra);
    }
}
