package us.bojie.instantmessagebo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.Activity;
import us.bojie.common.widget.PortraitView;
import us.bojie.factory.persistence.Account;
import us.bojie.instantmessagebo.R;
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
    @BindView(R.id.btn_action)
    FloatActionButton mAction;

    private NavUtils<Integer> mNavUtils;

    /**
     * MainActivity 显示的入口
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        if (Account.isComplete()) {
            // 判断用户信息是否完全，完全则走正常流程
            return super.initArgs(bundle);
        } else {
            UserActivity.show(this);
            return false;
        }
    }

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
                // 在群的界面的时候，点击顶部的搜索就进入群搜索界面
                // 其他都为人搜索的界面
                int type = Objects.equals(mNavUtils.getCurrentTab().extra, R.string.title_group) ?
                        SearchActivity.TYPE_GROUP : SearchActivity.TYPE_USER;
                SearchActivity.show(this, type);
                break;
            case R.id.btn_action:
                // 浮动按钮点击时，判断当前界面是群还是联系人界面
                // 如果是群，则打开群创建的界面
                if (Objects.equals(mNavUtils.getCurrentTab().extra, R.string.title_group)) {
                    // TODO 打开群创建界面
                } else {
                    // 如果是其他，都打开添加用户的界面
                    SearchActivity.show(this, SearchActivity.TYPE_USER);
                }
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

        // 对浮动按钮进行隐藏与显示的动画
        float transY = 0;
        float rotation = 0;
        if (Objects.equals(newTab.extra, R.string.title_home)) {
            // 主界面时隐藏
            transY = Ui.dipToPx(getResources(), 76);
        } else {
            if (Objects.equals(newTab.extra, R.string.title_group)) {
                // 群
                mAction.setImageResource(R.drawable.ic_group_add);
                rotation = -360;
            } else {
                // 联系人
                mAction.setImageResource(R.drawable.ic_contact_add);
                rotation = 360;
            }
        }

        // 开始动画
        // 旋转，Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }

}
