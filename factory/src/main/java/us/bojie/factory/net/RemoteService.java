package us.bojie.factory.net;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import us.bojie.factory.model.api.RspModel;
import us.bojie.factory.model.api.account.AccountRepModel;
import us.bojie.factory.model.api.account.RegisterModel;

/**
 * Created by bojiejiang on 10/29/17.
 * 网络请求的所有的接口
 */

public interface RemoteService {

    /**
     * 网络请求一个注册接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRepModel>> accountRegister(@Body RegisterModel model);
}
