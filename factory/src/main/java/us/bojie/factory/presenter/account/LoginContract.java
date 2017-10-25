package us.bojie.factory.presenter.account;

import us.bojie.factory.presenter.BaseContract;

/**
 * Created by bojiejiang on 10/24/17.
 */

public interface LoginContract {

    interface View extends BaseContract.View<Presenter> {
        // 注册成功
        void loginSuccess();
    }


    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String phone, String name, String password);
    }
}
