package us.bojie.factory.presenter.contact;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.List;

import us.bojie.factory.data.helper.UserHelper;
import us.bojie.factory.model.db.User;
import us.bojie.factory.model.db.User_Table;
import us.bojie.factory.persistence.Account;
import us.bojie.factory.presenter.BasePresenter;
import us.bojie.factory.utils.DiffUiDataCallback;

/**
 * Created by bojiejiang on 11/17/17.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {

    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
                        getView().getRecyclerAdapter().replace(tResult);
                        getView().onAdapterDataChanged();
                    }
                }).execute();

        // 加载网络数据
        UserHelper.refreshContacts();

/*
        // 转换为User
        final List<User> users = new ArrayList<>();
        for (UserCard userCard : userCards) {
            users.add(userCard.build());
        }

        // 丢到事物中保存数据库
        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
        definition.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(User.class)
                        .saveAll(users);
            }
        }).build().execute();

        // 网络的数据往往是新的，我们需要直接刷新到界面
        List<User> old = getView().getRecyclerAdapter().getItems();
        // 会导致数据顺序全部为新的数据集合
        // getView().getRecyclerAdapter().replace(users);
        diff(old, users);
        */

        // TODO 问题：
        // 1.关注后虽然存储数据库，但是没有刷新联系人
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全局刷新
        // 3.本地刷新和网络刷新，在添加到界面的时候会有可能冲突；导致数据显示异常
        // 4.如何识别已经在数据库中有这样的数据了
    }


    private void diff(List<User> oldList, List<User> newList) {
        // 进行数据对比
        DiffUtil.Callback callback = new DiffUiDataCallback<>(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 在对比完成后进行数据的赋值
        getView().getRecyclerAdapter().replace(newList);

        // 尝试刷新界面
        result.dispatchUpdatesTo(getView().getRecyclerAdapter());
        getView().onAdapterDataChanged();
    }
}
