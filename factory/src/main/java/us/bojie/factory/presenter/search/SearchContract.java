package us.bojie.factory.presenter.search;

import java.util.List;

import us.bojie.factory.model.card.GroupCard;
import us.bojie.factory.model.card.UserCard;
import us.bojie.factory.presenter.BaseContract;

/**
 * Created by bojiejiang on 11/12/17.
 */

public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    // 搜索人的界面
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    // 搜索群的界面
    interface GroupView extends BaseContract.View<Presenter> {
        void onSearchDone(List<GroupCard> groupCards);
    }
}
