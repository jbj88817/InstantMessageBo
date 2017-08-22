package us.bojie.common.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import us.bojie.common.R;
import us.bojie.common.widget.recycler.RecyclerAdapter;

public class GalleyView extends RecyclerView {

    private Adapter mAdapter = new Adapter();

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
        mAdapter.setListener(new RecyclerAdapter.AdapterListener<Image>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Image data) {

            }

            @Override
            public void onItemLongClick(RecyclerAdapter.ViewHolder holder, Image data) {

            }
        });
    }

    private static class Image {

    }

    private class Adapter extends RecyclerAdapter<Image> {

        @Override
        public void update(Image data, ViewHolder<Image> viewHolder) {

        }

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
