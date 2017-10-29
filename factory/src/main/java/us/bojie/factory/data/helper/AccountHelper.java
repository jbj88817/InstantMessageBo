package us.bojie.factory.data.helper;

import us.bojie.factory.data.DataSource;
import us.bojie.factory.model.api.account.RegisterModel;
import us.bojie.factory.model.db.User;

/**
 * Created by bojiejiang on 10/26/17.
 */

public class AccountHelper {

    /**
     * 注册的接口，异步的调用
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {

    }
}
