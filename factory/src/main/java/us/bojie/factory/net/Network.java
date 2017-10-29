package us.bojie.factory.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import us.bojie.common.Common;
import us.bojie.factory.Factory;

/**
 * Created by bojiejiang on 10/28/17.
 * 网络请求的封装
 */

public class Network {

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {
        // 得到一个OkHttp Client
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();

        // 设置电脑链接
        return builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }
}

