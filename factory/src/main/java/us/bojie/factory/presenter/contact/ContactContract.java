package us.bojie.factory.presenter.contact;

import us.bojie.factory.model.db.User;
import us.bojie.factory.presenter.BaseContract;

/**
 * Created by bojiejiang on 11/17/17.
 */

public interface ContactContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User> {

    }
}
