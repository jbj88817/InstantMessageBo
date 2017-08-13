package us.bojie.common.widget.recycler;

/**
 * Created by bojiejiang on 8/12/17.
 */

public interface AdapterCallback<T> {
    void update(T data, RecyclerAdapter.ViewHolder<T> viewHolder);
}
