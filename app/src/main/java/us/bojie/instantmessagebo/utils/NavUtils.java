package us.bojie.instantmessagebo.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 解决对Fragment的调度与重用问题，
 * 达到最优的Fragment切换
 */

public class NavUtils<T> {
    // 所有的Tab集合
    private final SparseArray<Tab<T>> tabs = new SparseArray<>();

    // 用于初始化的必须参数
    private final Context mContext;
    private final int mContainerId;
    private final FragmentManager mFragmentManager;
    private OnTabChangedListener<T> mListener;

    // 当前的一个选中的Tab
    private Tab<T> mCurrentTab;

    public NavUtils(Context context, int containerId,
                    FragmentManager fragmentManager, OnTabChangedListener<T> listener) {
        mContext = context;
        mContainerId = containerId;
        mFragmentManager = fragmentManager;
        mListener = listener;
    }

    /**
     * 添加Tab
     *
     * @param menuId Tab对应的菜单Id
     * @param tab    Tab
     */
    public NavUtils<T> add(int menuId, Tab<T> tab) {
        tabs.put(menuId, tab);
        return this;
    }

    public Tab<T> getCurrentTab() {
        return mCurrentTab;
    }

    /**
     * 执行点击菜单的操作
     *
     * @param menuId 菜单的Id
     * @return 是否能够处理这个点击
     */
    public boolean performClickMenu(int menuId) {
        Tab<T> tab = tabs.get(menuId);
        // 集合中寻找点击的菜单对应的Tab，
        // 如果有则进行处理
        if (tab != null) {
            doSelect(tab);
            return true;
        }
        return false;
    }

    /**
     * 进行真实的Tab选择操作
     *
     * @param tab Tab
     */
    private void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if (mCurrentTab != null) {
            oldTab = mCurrentTab;
            if (oldTab == tab) {
                // 如果说当前的Tab就是点击的Tab，
                // 那么我们不做处理
                notifyTabReselect(tab);
                return;
            }
        }
        mCurrentTab = tab;
        doTabChanged(mCurrentTab, oldTab);
    }

    /**
     * 进行Fragment的真实的调度操作
     * @param newTab 新的
     * @param oldTab 旧的
     */
    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if (oldTab != null) {
            if (oldTab.fragment != null) {
                // 从界面移除，但是还在Fragment的缓存空间中
                ft.detach(oldTab.fragment);
            }
        }

        if (newTab != null) {
            if (newTab.fragment == null) {
                // 首次新建
                Fragment fragment = Fragment.instantiate(mContext, newTab.clazz.getName(), null);
                // 缓存起来
                newTab.fragment = fragment;
                // 提交到FragmentManger
                ft.add(mContainerId, fragment, newTab.clazz.getName());
            } else {
                // 从FragmentManger的缓存空间中重新加载到界面中
                ft.attach(newTab.fragment);
            }
        }
        // 提交事务
        ft.commit();
        // 通知回调
        notifyTabSelect(newTab, oldTab);
    }

    /**
     * 回调我们的监听器
     *
     * @param newTab 新的Tab<T>
     * @param oldTab 旧的Tab<T>
     */
    private void notifyTabSelect(Tab<T> newTab, Tab<T> oldTab) {
        if (mListener != null) {
            mListener.onTabChanged(newTab, oldTab);
        }
    }

    private void notifyTabReselect(Tab<T> tab) {
        // TODO 二次点击Tab所做的操作
    }

    /**
     * 我们的所有的Tab基础属性
     *
     * @param <T> 范型的额外参数
     */
    public static class Tab<T> {

        public Tab(Class<?> clazz, T extra) {
            this.clazz = clazz;
            this.extra = extra;
        }

        public Class<?> clazz;
        // 额外的字段，用户自己设定需要使用
        public T extra;
        // 内部缓存的对应的Fragment，
        // Package权限，外部无法使用
        Fragment fragment;

    }

    // 定义事件处理完成后的回调接口
    public interface OnTabChangedListener<T> {
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }
}
