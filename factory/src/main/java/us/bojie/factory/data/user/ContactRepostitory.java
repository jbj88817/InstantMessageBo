package us.bojie.factory.data.user;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.LinkedList;
import java.util.List;

import us.bojie.factory.data.DataSource;
import us.bojie.factory.data.helper.DbHelper;
import us.bojie.factory.model.db.User;
import us.bojie.factory.model.db.User_Table;
import us.bojie.factory.persistence.Account;

/**
 * Created by bojiejiang on 11/29/17.
 * 联系人仓库
 */

public class ContactRepostitory implements ContactDataSource,
        QueryTransaction.QueryResultListCallback<User>,
        DbHelper.ChangedListener<User> {

    private DataSource.SucceedCallback<List<User>> mCallback;
    private final List<User> mUsers = new LinkedList<>();

    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {

        mCallback = callback;
        // 对数据辅助工具类添加一个数据更新的监听
        DbHelper.addChangedListener(User.class, this);
        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();

    }

    @Override
    public void dispose() {
        mCallback = null;
        // 取消对数据集合的监听
        DbHelper.removeChangedListener(User.class, this);
    }

    @Override
    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
        if (tResult.size() == 0) {
            mUsers.clear();
            notifyDataChange();
            return;
        }

        // 转变为数组
        User[] users = tResult.toArray(new User[0]);
        // 回到数据集更新的操作中
        onDataSave(users);
    }

    @Override
    public void onDataSave(User... list) {
        boolean isChanged = false;
        // 当数据库数据变更的操作
        for (User user : list) {
            // 是关注的人，同时不是我自己
            if (isRequired(user)) {
                insertOrUpdate(user);
                isChanged = true;
            }
        }
        if (isChanged) {
            // 有数据变更，则进行界面刷新
            notifyDataChange();
        }
    }

    @Override
    public void onDataDelete(User... list) {
        // 但数据库数据删除的操作
        boolean isChanged = false;
        for (User user : list) {
            if (mUsers.remove(user)) {
                isChanged = true;
            }
        }

        if (isChanged) {
            // 有数据变更，则进行界面刷新
            notifyDataChange();
        }
    }

    private void insertOrUpdate(User user) {
        int index = indexOf(user);
        if (index >= 0) {
            replace(index, user);
        } else {
            insert(user);
        }
    }

    private void replace(int index, User user) {
        mUsers.remove(index);
        mUsers.add(index, user);
    }

    private void insert(User user) {
        mUsers.add(user);
    }

    private int indexOf(User user) {
        int index = -1;
        for (User user1 : mUsers) {
            index++;
            if (user1.isSame(user)) {
                return index;
            }
        }
        return -1;
    }

    private void notifyDataChange() {
        if (mCallback != null) {
            mCallback.onDataLoaded(mUsers);
        }
    }

    /**
     * 检查一个User是否是我需要关注的数据
     *
     * @param user User
     * @return True是我关注的数据
     */
    private boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
