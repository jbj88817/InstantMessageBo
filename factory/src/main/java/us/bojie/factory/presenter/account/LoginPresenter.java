package us.bojie.factory.presenter.account;

import us.bojie.factory.presenter.BasePresenter;

/**
 * Created by bojiejiang on 10/29/17.
 * 登录的逻辑实现
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }
}
