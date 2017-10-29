package us.bojie.instantmessagebo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;

import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.compat.UiCompat;

import us.bojie.common.app.Activity;
import us.bojie.factory.persistence.Account;
import us.bojie.instantmessagebo.activities.AccountActivity;
import us.bojie.instantmessagebo.activities.MainActivity;
import us.bojie.instantmessagebo.fragments.assist.PermissionsFragment;

public class LaunchActivity extends Activity {

    private ColorDrawable mBgDrawable;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 拿到跟布局
        View root = findViewById(R.id.activity_launch);
        // 获取颜色
        int color = UiCompat.getColor(getResources(), R.color.colorPrimary);
        // 创建一个Drawable
        ColorDrawable drawable = new ColorDrawable(color);
        // 设置给背景
        root.setBackground(drawable);
        mBgDrawable = drawable;
    }

    @Override
    protected void initData() {
        super.initData();
        // 动画进入到50%等待PushId获取到
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
                // 检查等待状态
                waitPushReceiverId();
            }
        });
    }

    /**
     * 等待个推框架对我们的PushId设置好值
     */
    private void waitPushReceiverId() {
        // 如果拿到了PushId

        if (!TextUtils.isEmpty(Account.getPushId())) {
            // 跳转
            skip();
            return;
        }


        // 循环等待
        getWindow().getDecorView()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waitPushReceiverId();
                    }
                }, 500);
    }

    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void skip() {
        startAnim(1f, new Runnable() {
            @Override
            public void run() {
                reallySkip();
            }
        });
    }

    private void reallySkip() {
        // 权限检测，跳转
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            AccountActivity.show(this);
            finish();
        }
    }


    /**
     * 给背景设置一个动画
     *
     * @param endProgress 动画的结束进度
     * @param endCallback 动画结束时触发
     */
    private void startAnim(float endProgress, final Runnable endCallback) {
        // 获取一个最终的颜色
        int finalColor = Resource.Color.WHITE; // UiCompat.getColor(getResources(), R.color.white);
        // 运算当前进度的颜色
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int endColor = (int) evaluator
                .evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        // 构建一个属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, endColor);
        valueAnimator.setDuration(1500); //1.5s
        valueAnimator.setIntValues(mBgDrawable.getColor(), endColor);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        valueAnimator.start();
    }

    private final Property<LaunchActivity, Object> property =
            new Property<LaunchActivity, Object>(Object.class, "color") {
                @Override
                public Object get(LaunchActivity object) {
                    return mBgDrawable.getColor();
                }

                @Override
                public void set(LaunchActivity object, Object value) {
                    object.mBgDrawable.setColor((Integer) value);
                }
            };
}
