package us.bojie.factory.presenter.account;

import android.text.TextUtils;

import java.util.regex.Pattern;

import us.bojie.common.Common;
import us.bojie.factory.data.helper.AccountHelper;
import us.bojie.factory.model.api.account.RegisterModel;
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
        // 调用开始方法，在start中默认启动了Loading
        start();


        if (!checkMobile(phone)) {
            // 提示
        } else if (name.length() < 2) {
            // 姓名需要大于2位
        } else if (password.length() < 2) {
            // 密码需要大于6位
        } else {
            // 进行网络请求

            // 构造Model，进行请求调用
            RegisterModel model = new RegisterModel(phone, password, name);
            AccountHelper.register(model);
        }

    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 手机号码
     * @return 合法为True
     */
    @Override
    public boolean checkMobile(String phone) {
        // 手机号不为空，并且满足格式
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);

    }
}
