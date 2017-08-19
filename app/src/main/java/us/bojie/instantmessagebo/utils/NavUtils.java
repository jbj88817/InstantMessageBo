package us.bojie.instantmessagebo.utils;

import android.app.FragmentManager;
import android.content.Context;
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

    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {

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
        public Class<?> clazz;
        // 额外的字段，用户自己设定需要使用
        public T extra;
    }

    // 定义事件处理完成后的回调接口
    public interface OnTabChangedListener<T> {
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }
}
