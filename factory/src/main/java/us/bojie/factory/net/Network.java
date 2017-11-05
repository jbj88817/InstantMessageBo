package us.bojie.factory.net;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import us.bojie.common.Common;
import us.bojie.factory.Factory;
import us.bojie.factory.persistence.Account;

/**
 * Created by bojiejiang on 10/28/17.
 * 网络请求的封装
 */

public class Network {

    private static Network instance;
    private Retrofit mRetrofit;

    static {
        instance = new Network();
    }

    private Network() {

    }

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {
        // 得到一个OkHttp Client
        if (instance.mRetrofit != null) {
            return instance.mRetrofit;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 拿到我们的请求
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        if (!TextUtils.isEmpty(Account.getToken())) {
                            builder.addHeader("token", Account.getToken());
                        }
//                        builder.addHeader("Content-Type", "application/json");
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();

        // 设置电脑链接
        instance.mRetrofit = builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
        return instance.mRetrofit;
    }

    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }
}

