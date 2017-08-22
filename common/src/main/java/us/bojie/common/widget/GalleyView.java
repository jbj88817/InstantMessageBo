package us.bojie.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import us.bojie.common.R;
import us.bojie.common.widget.recycler.RecyclerAdapter;

public class GalleyView extends RecyclerView {

    private static final int LOADER_ID = 0x0100;
    private static final int MAX_IMAGE_COUNT = 3; // 最大选中图片数量
    private Adapter mAdapter = new Adapter();
    private LoaderCallback mLoaderCallback = new LoaderCallback();
    private List<Image> mSelectedImages = new LinkedList<>();

    public GalleyView(Context context) {
        super(context);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(), 4));
        setAdapter(mAdapter);
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Image>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Image image) {
                // Cell点击操作，如果说我们的点击是允许的，那么更新对应的Cell的状态
                // 然后更新界面，同理；如果说不能允许点击（已经达到最大的选中数量）那么就不刷新界面
                if (onItemSelectClick(image)) {
                    //noinspection unchecked
                    holder.updateData(image);
                }
            }
        });
    }

    /**
     * 初始化方法
     *
     * @param loaderManager Loader管理器
     * @return 任何一个LOADER_ID，可用于销毁Loader
     */
    public int setup(LoaderManager loaderManager) {
        loaderManager.initLoader(LOADER_ID, null, mLoaderCallback);
        return LOADER_ID;
    }

    /**
     * Cell点击的具体逻辑
     *
     * @param image Image
     * @return True，代表我进行了数据更改，你需要刷新；反之不刷新
     */
    private boolean onItemSelectClick(Image image) {
        // 是否需要进行刷新
        boolean notifyRefresh;
        if (mSelectedImages.contains(image)) {
            // 如果之前在那么现在就移除
            mSelectedImages.remove(image);
            image.isSelect = false;
            // 状态已经改变则需要更新
            notifyRefresh = true;
        } else {
            if (mSelectedImages.size() >= MAX_IMAGE_COUNT) {
                // Toast 一个提示
                notifyRefresh = false;
            } else {
                mSelectedImages.add(image);
                image.isSelect = true;
                notifyRefresh = true;
            }
        }

        // 如果数据有更改，
        // 那么我们需要通知外面的监听者我们的数据选中改变了
        if (notifyRefresh) {
            notifySelectChanged();
        }
        return true;
    }

    /**
     * 得到选中的图片的全部地址
     *
     * @return 返回一个数组
     */
    public String[] getSelectedPath() {
        String[] paths = new String[mSelectedImages.size()];
        int index = 0;
        for (Image image : mSelectedImages) {
            paths[index++] = image.path;
        }
        return paths;
    }

    /**
     * 可以进行清空选中的图片
     */
    public void clear() {
        for (Image image : mSelectedImages) {
            // 一定要先重置状态
            image.isSelect = false;
        }
        mSelectedImages.clear();
        // 通知更新
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 通知选中状态改变
     */
    private void notifySelectChanged() {

    }

    /**
     * 用于实际的数据加载的Loader Callback
     */
    private class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            // 创建一个Loader
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            // 当Loader加载完成时
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            // 当Loader销毁或者重置了
        }
    }

    /**
     * 内部的数据结构
     */
    private static class Image {
        int id; // 数据的ID
        String path; // 图片的路径
        long date; // 图片的创建日期
        boolean isSelect; // 是否选中

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Image image = (Image) o;

            return path != null ? path.equals(image.path) : image.path == null;

        }

        @Override
        public int hashCode() {
            return path != null ? path.hashCode() : 0;
        }
    }

    private class Adapter extends RecyclerAdapter<Image> {

        @Override
        protected int getItemViewType(int position, Image data) {
            return R.layout.cell_galley;
        }

        @Override
        protected ViewHolder<Image> onCreateViewHolder(android.view.View root, int viewType) {
            return new GalleyView.ViewHolder(root);
        }
    }

    private class ViewHolder extends RecyclerAdapter.ViewHolder<Image> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Image data) {

        }
    }
}
