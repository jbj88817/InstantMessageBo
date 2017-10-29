package us.bojie.instantmessagebo;

import com.igexin.sdk.PushManager;

import us.bojie.common.app.MyApplication;
import us.bojie.factory.Factory;

/**
 * Created by bojiejiang on 9/19/17.
 */

public class App extends MyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(this);
    }
}
