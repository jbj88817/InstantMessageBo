package us.bojie.factory.data.helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.factory.R;
import us.bojie.factory.data.DataSource;
import us.bojie.factory.model.api.RspModel;
import us.bojie.factory.model.api.account.AccountRepModel;
import us.bojie.factory.model.api.account.RegisterModel;
import us.bojie.factory.model.db.User;
import us.bojie.factory.net.Network;
import us.bojie.factory.net.RemoteService;

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
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 得到一个Call
        Call<RspModel<AccountRepModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<AccountRepModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRepModel>> call,
                                   Response<RspModel<AccountRepModel>> response) {
                // 请求成功返回
                // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
                RspModel<AccountRepModel> rspModel = response.body();
                if (rspModel != null && rspModel.success()) {
                    // 拿到实体
                    AccountRepModel accountRepModel = rspModel.getResult();
                    // 判断绑定状态，是否绑定设备
                    if (accountRepModel.isBind()) {
                        User user = accountRepModel.getUser();
                        // TODO 进行的是数据库写入和缓存绑定
                        // 然后返回
                        callback.onDataLoaded(user);
                    } else {
                        // 进行绑定的唤起
                        bindPush(callback);
                    }
                } else {
                    // TODO 对返回的RspModel中的失败的Code进行解析，解析到对应的String资源上面
                    // callback.onDataNotAvailable();
                }

            }

            @Override
            public void onFailure(Call<RspModel<AccountRepModel>> call, Throwable t) {
                // 网络请求失败
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 对设备Id进行绑定的操作
     *
     * @param callback Callback
     */
    public static void bindPush(final DataSource.Callback<User> callback) {

    }
}
