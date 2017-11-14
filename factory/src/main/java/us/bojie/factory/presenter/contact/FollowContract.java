package us.bojie.factory.presenter.contact;

import us.bojie.factory.model.card.UserCard;
import us.bojie.factory.presenter.BaseContract;

/**
 * Created by bojiejiang on 11/13/17.
 * 关注的接口定义
 */

public interface FollowContract {
    interface Presenter extends BaseContract.Presenter {
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        void onFollowSucceed(UserCard userCard);
    }
}
