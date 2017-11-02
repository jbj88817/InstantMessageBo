package us.bojie.factory.data.helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.factory.Factory;
import us.bojie.factory.R;
import us.bojie.factory.data.DataSource;
import us.bojie.factory.model.api.RspModel;
import us.bojie.factory.model.api.account.AccountRspModel;
import us.bojie.factory.model.api.account.RegisterModel;
import us.bojie.factory.model.db.User;
import us.bojie.factory.net.Network;
import us.bojie.factory.net.RemoteService;
import us.bojie.factory.persistence.Account;

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
    public static void register(final RegisterModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call,
                                   Response<RspModel<AccountRspModel>> response) {
                // 请求成功返回
                // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel != null && rspModel.success()) {
                    // 拿到实体
                    AccountRspModel accountRepModel = rspModel.getResult();
                    // 获取我的信息
                    User user = accountRepModel.getUser();
                    // 进行的是数据库写入和缓存绑定
                    // 第一种，直接保存
                    user.save();
                        /*
                        // 第二种通过ModelAdapter
                        FlowManager.getModelAdapter(User.class)
                                .save(user);

                        // 第三种，事务中
                        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                        definition.beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {
                                FlowManager.getModelAdapter(User.class)
                                        .save(user);
                            }
                        }).build().execute();
                        */
                    // 同步到XML持久化中
                    Account.login(accountRepModel);

                    // 判断绑定状态，是否绑定设备
                    if (accountRepModel.isBind()) {
                        // 然后返回
                        callback.onDataLoaded(user);
                    } else {
                        // 进行绑定的唤起
                        bindPush(callback);
                    }
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
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
        // TODO 先抛出一个错误，其实是我们的绑定没有进行
        Account.setBind(true);
    }
}
