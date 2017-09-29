package us.bojie.factory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import us.bojie.common.app.MyApplication;

/**
 * Created by bojiejiang on 9/25/17.
 */

public class Factory {
    // 单例模式
    private static final Factory instance;
    private final Executor mExecutor;

    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
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

}
