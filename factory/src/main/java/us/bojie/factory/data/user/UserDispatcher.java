package us.bojie.factory.data.user;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import us.bojie.factory.data.helper.DbHelper;
import us.bojie.factory.model.card.UserCard;
import us.bojie.factory.model.db.User;

/**
 * Created by bojiejiang on 11/25/17.
 */

public class UserDispatcher implements UserCenter {
    private static UserCenter instance;
    // 单线程池；处理卡片一个个的消息进行处理
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    public static UserCenter getInstance() {
        if (instance == null) {
            synchronized (UserDispatcher.class) {
                if (instance == null) {
                    instance = new UserDispatcher();
                }
            }
        }
        return instance;
    }

    @Override
    public void dispatch(UserCard... userCards) {
        if (userCards == null || userCards.length == 0) {
            return;
        }

        // 丢到单线程池中
        mExecutor.execute(new UserCardHandler(userCards));
    }

    /**
     * 线程调度的时候会触发run方法
     */
    private class UserCardHandler implements Runnable {
        private final UserCard[] cards;

        UserCardHandler(UserCard[] cards) {
            this.cards = cards;
        }

        @Override
        public void run() {
            // 被线程调度的时候触发
            List<User> users = new ArrayList<>();
            // 进行过滤操作
            for (UserCard card : cards) {
                if (card == null || TextUtils.isEmpty(card.getId())) {
                    continue;
                }
                users.add(card.build());
            }

            // 进行数据库存储，并分发通知, 异步的操作
            DbHelper.save(User.class, users.toArray(new User[0]));
        }
    }
}
