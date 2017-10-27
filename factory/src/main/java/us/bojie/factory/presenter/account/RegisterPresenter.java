package us.bojie.factory.presenter.account;

import us.bojie.factory.presenter.BasePresenter;

/**
 * Created by bojiejiang on 10/26/17.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements
        RegisterContract.Presenter {
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {

    }

    @Override
    public boolean checkMobile(String phone) {
        return false;
    }
}
