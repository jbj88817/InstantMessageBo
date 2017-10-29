package us.bojie.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import us.bojie.common.app.MyApplication;

/**
 * Created by bojiejiang on 9/25/17.
 */

public class Factory {
    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor mExecutor;
    // 全局的Gson
    private final Gson mGson;

    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // TODO 设置一个过滤器，数据库级别的Model不进行Json转换
                //.setExclusionStrategies()
                .create();
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static MyApplication app() {
        return (MyApplication) MyApplication.getInstance();
    }

    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.mExecutor.execute(runnable);
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     *
     * @return Gson
     */
    public static Gson getGson() {
        return instance.mGson;
    }
}
