package us.bojie.instantmessagebo;

import android.support.design.widget.BottomNavigationView;
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

public class MainActivity extends Activity {

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
}
