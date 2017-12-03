package us.bojie.factory.presenter.contact;

import android.support.v7.util.DiffUtil;

import java.util.List;

import us.bojie.common.widget.recycler.RecyclerAdapter;
import us.bojie.factory.data.DataSource;
import us.bojie.factory.data.helper.UserHelper;
import us.bojie.factory.data.user.ContactDataSource;
import us.bojie.factory.data.user.ContactRepostitory;
import us.bojie.factory.model.db.User;
import us.bojie.factory.presenter.BaseRecyclerPresenter;
import us.bojie.factory.utils.DiffUiDataCallback;

/**
 * Created by bojiejiang on 11/17/17.
 */

public class ContactPresenter extends BaseRecyclerPresenter<User, ContactContract.View>
        implements ContactContract.Presenter,
        DataSource.SucceedCallback<List<User>> {

    private ContactDataSource mSource;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        mSource = new ContactRepostitory();
    }

    @Override
    public void start() {
        super.start();

        // 进行本地的数据加载，并添加监听
        mSource.load(this);

        // 加载网络数据
        UserHelper.refreshContacts();

        // TODO 问题：
        // 1.关注后虽然存储数据库，但是没有刷新联系人
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全局刷新
        // 3.本地刷新和网络刷新，在添加到界面的时候会有可能冲突；导致数据显示异常
        // 4.如何识别已经在数据库中有这样的数据了
    }

    // 运行到这里的时候是子线程
    @Override
    public void onDataLoaded(List<User> users) {
        // 无论怎么操作，数据变更，最终都会通知到这里来

        final ContactContract.View view = getView();
        if (view == null) {
            return;
        }

        RecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> old = adapter.getItems();

        // 进行数据对比
        DiffUtil.Callback callback = new DiffUiDataCallback<>(old, users);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 调用基类方法进行界面刷新
        refreshData(result, users);
    }

    @Override
    public void destroy() {
        // 当界面销毁的时候，我们应该把数据监听进行销毁
        mSource.dispose();
        super.destroy();
    }
}
